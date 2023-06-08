package com.food.ordering.system.service;

import com.food.ordering.system.entity.Order;
import com.food.ordering.system.entity.Resturant;
import com.food.ordering.system.event.OrderCancelledEvent;
import com.food.ordering.system.event.OrderCreatedEvent;
import com.food.ordering.system.event.OrderPaidEvent;
import com.food.ordering.system.exception.OrderDomainException;
import lombok.extern.slf4j.Slf4j;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Slf4j
public class OrderDomainServiceImpl implements OrderDomainService {
    private final static String ZonedIDFormat = "UTC";

    // ============================================================================
    @Override
    public OrderPaidEvent PayOrder(Order order) {
        order.pay();
        log.info("order with id {} has been paid successfully", order.getId());
        return new OrderPaidEvent(order, ZonedDateTime.now(ZoneId.of(ZonedIDFormat)));
    }
    // ============================================================================


    // ============================================================================

    @Override
    public OrderCreatedEvent CreateAndInitiateOrder(Order order, Resturant resturant) {
        // validate that the resturent is active and currently working
        validateResturantIsActive(resturant);

        // get the products prices from database via the resturant entity, and set them as the prices of the order-items by matching them
        setOrderItemsPrices(order, resturant);

        // validate the initialization of the order
        order.validateOrder();

        // initialize the order
        order.initializeOrder();

        log.info("order with id {} has been validated and initialized successfully", order.getId());

        // return the event response
        return new OrderCreatedEvent(order, ZonedDateTime.now(ZoneId.of(ZonedIDFormat)));
    }

    private void validateResturantIsActive(Resturant resturant) {
        if (!resturant.isResturantActive())
            throw new OrderDomainException("resturant is not active at the current time");
    }

    private void setOrderItemsPrices(Order order, Resturant resturant) {
        // we need to set the order-item.product data from the database resturant entity
        order.getOrderItems().forEach(orderItem -> resturant.getProducts().forEach(resturantProduct -> {
            if (orderItem.getProduct().equals(resturantProduct))
                orderItem.getProduct().setProductNameAndPrice(resturantProduct.getProductName(), resturantProduct.getProductPrice());
        }));
    }
    // ============================================================================


    // ============================================================================

    @Override
    public void CancelOrder(Order order, List<String> failMsgs) {
        order.cancel(failMsgs);
        log.info("order with id = {} has been cancelled", order.getId());
    }
    // ============================================================================


    // ============================================================================

    @Override
    public OrderCancelledEvent CancelOrderPayment(Order order, List<String> failMsgs) {
        order.initCancellation(failMsgs);
        log.info("the payment of order with id = {} has been cancelled", order.getId());
        return new OrderCancelledEvent(order, ZonedDateTime.now(ZoneId.of(ZonedIDFormat)));
    }
    // ============================================================================


    // ============================================================================

    @Override
    public void ApproveOrder(Order order) {
        order.approve();

        log.info("order with id = {} has been approved", order.getId());
    }
    // ============================================================================
}
