package com.food.ordering.system.mapper;

import com.food.ordering.system.domain.valueobject.CustomerID;
import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.domain.valueobject.ProductID;
import com.food.ordering.system.domain.valueobject.ResturantID;
import com.food.ordering.system.dtos.create.CreateOrderCommandRequestDto;
import com.food.ordering.system.dtos.create.CreateOrderCommandResponseDto;
import com.food.ordering.system.dtos.create.DeliveryAddressDto;
import com.food.ordering.system.dtos.create.OrderItemDto;
import com.food.ordering.system.entity.Order;
import com.food.ordering.system.entity.OrderItem;
import com.food.ordering.system.entity.Product;
import com.food.ordering.system.entity.Resturant;
import com.food.ordering.system.valueobject.OrderItemQuantity;
import com.food.ordering.system.valueobject.StreetAddress;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class OrderDataMapper {
    /**
     * convert the createOrderCommandDto -> Restaurant entity
     *
     * @param createOrderDto
     * @return Restaurant entity
     */
    public Resturant CreateOrderDtoToRestaurant(CreateOrderCommandRequestDto createOrderDto) {
        // currently we don't have anything about the restaurant rather than the id, we will get the rest from the database
        return new Resturant.Builder()
                .resturantID(new ResturantID(createOrderDto.getRestaurantID()))
                .products(
                        createOrderDto.getOrderItems()
                                .stream().
                                map(OI -> new Product(new ProductID(OI.getProductID()))).toList()
                )
                .build();
    }

    /**
     * convert the createOrderCommandDto -> Order entity
     *
     * @param createOrderDto
     * @return Order entity
     */
    public Order CreateOrderDtoToOrder(CreateOrderCommandRequestDto createOrderDto) {
        return new Order.Builder()
                .resturantID(new ResturantID(createOrderDto.getRestaurantID()))
                .customerID(new CustomerID(createOrderDto.getCustomerID()))
                .shippingAddress(addressDtoToShippingAddress(createOrderDto.getAddress()))
                .orderItems(orderItemsDtoToOrderItemsEntity(createOrderDto.getOrderItems()))
                .totalPrice(new Money(createOrderDto.getTotalPrice()))
                .build();
    }

    private List<OrderItem> orderItemsDtoToOrderItemsEntity(List<OrderItemDto> orderItems) {
        return orderItems.stream()
                .map(orderItemDto ->
                        new OrderItem.Builder()
                                .price(new Money(orderItemDto.getPrice()))
                                .quantity(new OrderItemQuantity(orderItemDto.getQty()))
                                .subTotal(new Money(orderItemDto.getSubTotal()))
                                .product(new Product(new ProductID(orderItemDto.getProductID())))
                                .build()
                ).toList();
    }

    private StreetAddress addressDtoToShippingAddress(DeliveryAddressDto address) {
        return new StreetAddress(UUID.randomUUID(), address.getStreet(), address.getPostalCode(), address.getCity());
    }

    public CreateOrderCommandResponseDto OrderToCreateOrderResponseDto(Order order) {
        return CreateOrderCommandResponseDto.builder()
                .orderID(order.getId().getValue())
                .status(order.getStatus())
                .build();
    }
}
