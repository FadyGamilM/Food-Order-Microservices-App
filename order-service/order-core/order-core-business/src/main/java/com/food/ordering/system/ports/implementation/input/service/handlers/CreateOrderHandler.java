package com.food.ordering.system.ports.implementation.input.service.handlers;

import com.food.ordering.system.dtos.create.CreateOrderCommandRequestDto;
import com.food.ordering.system.dtos.create.CreateOrderCommandResponseDto;
import com.food.ordering.system.entity.Order;
import com.food.ordering.system.entity.Resturant;
import com.food.ordering.system.exception.OrderDomainException;
import com.food.ordering.system.mapper.OrderDataMapper;
import com.food.ordering.system.ports.output.repository.CustomerRepository;
import com.food.ordering.system.ports.output.repository.OrderRepository;
import com.food.ordering.system.ports.output.repository.RestaurantRepository;
import com.food.ordering.system.service.OrderDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Component
public class CreateOrderHandler {
    // required dependencies to complete the handler logic
    private final OrderDomainService _orderDomainService;

    // * to persist the order entity
    private final OrderRepository _orderRepo;

    // * to check the customer existence in db
    private final CustomerRepository _customerRepo;
    private final RestaurantRepository _restaurantRepo;
    private final OrderDataMapper _orderMapper;

    public CreateOrderHandler(OrderDomainService orderDomainService,
                              OrderRepository orderRepo,
                              CustomerRepository customerRepo,
                              RestaurantRepository restaurantRepo,
                              OrderDataMapper orderMapper) {
        _orderDomainService = orderDomainService;
        _orderRepo = orderRepo;
        _customerRepo = customerRepo;
        _restaurantRepo = restaurantRepo;
        _orderMapper = orderMapper;
    }


    @Transactional
    public CreateOrderCommandResponseDto createOrder(CreateOrderCommandRequestDto orderDto) {
        // TODO :
        //  1. Checks if the customer who created this order exists or not using customer repo
        //  2. checks if the restaurant exists by fetching it from database
        //  3. map the given inputs fields from the client to createa domain order entity
        //  4. call the domain service to validate the order and initiate it and get the CreatedOrderEvent
        //  5. call the order repository to persist the created order
        checkIfCustomerExists(orderDto.getCustomerID());
        var restaurant = _orderMapper.CreateOrderDtoToRestaurant(orderDto);
        var DbRestaurant = checkIfRestaurantExists(restaurant, orderDto.getRestaurantID());
        var order = _orderMapper.CreateOrderDtoToOrder(orderDto);
        var orderCreatedEvent = _orderDomainService.CreateAndInitiateOrder(order, DbRestaurant);
        var persistedOrder = PersistOrder(order);
        return _orderMapper.OrderToCreateOrderResponseDto(persistedOrder);
    }

    private Order PersistOrder(Order order) {
        var savedOrder = _orderRepo.saveOrder(order);
        if (savedOrder != null) {
            log.info("order has been persisted in database successfully with id = {}", savedOrder.getId().getValue());
            return savedOrder;
        }
        log.error("couldn't save an order with id = " + order.getId().getValue() + " in DB");
        throw new OrderDomainException("couldn't save an order with id = " + order.getId().getValue() + " in DB");
    }


    private void checkIfCustomerExists(UUID customerID) {
        var customerExists = _customerRepo.findCustomerByID(customerID);
        if (customerExists.isEmpty()) {
            log.error("couldn't find a customer with id = {}", customerID);
            throw new OrderDomainException("customer with customer id = " + customerID + " who ordered this order is not exists");
        }
    }

    private Resturant checkIfRestaurantExists(Resturant resturant, UUID restaurantID) {
        var restaurantExists = _restaurantRepo.findRestaurantDetails(resturant);
        if (restaurantExists.isEmpty()) {
            log.error("couldn't find a restaurant with id = {}", restaurantID);
            throw new OrderDomainException("No restaurant with id = {}" + restaurantID);
        }
        return restaurantExists.get();
    }
}
