package com.food.ordering.system.entity;

import com.food.ordering.system.domain.entity.AggregateRoot;
import com.food.ordering.system.domain.valueobject.*;
import com.food.ordering.system.exception.OrderDomainException;
import com.food.ordering.system.valueobject.OrderItemID;
import com.food.ordering.system.valueobject.StreetAddress;
import com.food.ordering.system.valueobject.TrackingID;

import javax.sound.midi.Track;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class
Order extends AggregateRoot<OrderID> {

    // ? : private constructor for Order entity to be used in builder-pattern
    private Order(Builder builder) {
        super.setId(builder.orderID);
        this.customerId = builder.customerID;
        this.resturantID = builder.resturantID;
        this.trackingId = builder.trackingID;
        this.orderItems = builder.orderItems;
        this.totalPrice = builder.totalPrice;
        this.failureMsgs = builder.failureMsgs;
        this.status = builder.status;
        this.shippingAddress = builder.shippingAddress;
    }

    // ? : private final fields of the Order entity
    private final CustomerID customerId;
    private final ResturantID resturantID;
    private final StreetAddress shippingAddress;

    //    used to track the order and i use this field to not use the PK in our application logic
    private TrackingID trackingId;

    private final Money totalPrice;

    // each order has a lsit of order items
    private List<OrderItem> orderItems = new ArrayList<OrderItem>();

    private OrderStatus status; // not final because it will change through the life-cycle of the app

    private List<String> failureMsgs = new ArrayList<String>();


    // ? : implementation of the builder pattern
    public static class Builder {
        private OrderID orderID;
        private CustomerID customerID;
        private ResturantID resturantID;
        private TrackingID trackingID;
        private Money totalPrice;
        private List<OrderItem> orderItems;
        private List<String> failureMsgs;
        private OrderStatus status;
        private StreetAddress shippingAddress;

        public Builder orderID(OrderID val) {
            this.orderID = val;
            return this;
        }

        public Builder resturantID(ResturantID val) {
            this.resturantID = val;
            return this;
        }

        public Builder customerID(CustomerID val) {
            this.customerID = val;
            return this;
        }

        public Builder trackingID(TrackingID val) {
            this.trackingID = val;
            return this;
        }


        public Builder totalPrice(Money val) {
            this.totalPrice = val;
            return this;
        }


        public Builder failureMsgs(List<String> val) {
            this.failureMsgs = val;
            return this;
        }

        public Builder status(OrderStatus val) {
            this.status = val;
            return this;
        }

        public Builder orderItems(List<OrderItem> val) {
            this.orderItems = val;
            return this;
        }

        public Builder shippingAddress(StreetAddress val) {
            this.shippingAddress = val;
            return this;
        }


        public Order build() {
            return new Order(this);
        }
    }

    // ? : domain logic methods
    // * gives initial values to the Order entity fields
    public void initializeOrder() {
        // initialize the identifier
        this.initializeOrderID(UUID.randomUUID());
        // initialize the status to start with PENDING
        this.initializeOrderAsPENDING();
        // initialize the order items
        orderItems = this.initializeOrderItems();
        // initialize the tracking identifier
        this.initializeOrderTrackingID(UUID.randomUUID());
    }

    private void initializeOrderID(UUID id) {
        this.setId(new OrderID(id));
    }

    private void initializeOrderAsPENDING() {
        this.status = OrderStatus.PENDING;
    }

    private void initializeOrderTrackingID(UUID id) {
        this.trackingId = new TrackingID(id);
    }

    private List<OrderItem> initializeOrderItems() {
        // start with id = 1
        Long order_item_id = 1L;
        for (OrderItem OI : this.orderItems) {
            OI.initializeOrderItem(this.getId(), new OrderItemID(order_item_id++));
        }
        return this.orderItems;
    }


    // * validate the order's total price, prices of the ordered items of this order, and the order initial status
    public void validateOrder() {
        validateOrderInitialState();
        validateOrderPrice();
        validateOrderItemsPriceWithTotalPrice();
    }

    private void validateOrderItemsPriceWithTotalPrice() {
        // * sum up the total prices of all items and compare them to the total price of the order (that we had validated previously)
        var calculatedTotalPrice = this.orderItems.stream().map(OI -> {
            this.validateOrderItemPrice(OI);
            return OI.getSubTotal();
        }).reduce(Money.ZERO_MONEY, Money::AddAmount);
        // * now compare with the calculated total price of the order
        if (!calculatedTotalPrice.equals(this.totalPrice))
            throw new OrderDomainException("total price is not correctly calculated !");
    }

    private void validateOrderItemPrice(OrderItem o) {
        if (!o.validateOrderItemPrice())
            throw new OrderDomainException(String.format("the price of order item %s is not valid", o.getProduct().getProductName()));
    }

    private void validateOrderPrice() {
        if (!this.totalPrice.IsValidAmount())
            throw new OrderDomainException("order price must be greater than ZERO !");
    }

    private void validateOrderInitialState() {
        if (this.getId() != null || this.status != null)
            throw new OrderDomainException("order is not in the right initial state !");

    }

    /**
     * the order service will publish an event to notify the payment service to proceed with the payment process
     * and we will change the status from PENDING -> PAID
     */
    public void pay() {
        if (this.status != OrderStatus.PENDING)
            throw new OrderDomainException("Order is not in the valid state to be raedy for payment process");

        this.status = OrderStatus.PAID;
    }

    /**
     * the order service published an event to resturent with PAID order, the resturent approved the order and published the event with approval
     * the order-service consumed that and will change the status from PAID -> APPROVED
     */
    public void approve() {
        // ORDER-SERVICE received the
        if (this.status != OrderStatus.PAID)
            throw new OrderDomainException("Order is not in the valid state for resturent to approve the order");
        this.status = OrderStatus.APPROVED;
    }

    /**
     * the order publish an event after the payment is completed, and the order is PAID, but the resutrent has refused the order and published
     * an event with that refuse response, the order service consumed the event from the resturant.
     * so the order-service will change the status from PAID -> CANCELLING
     */
    public void initCancellation(List<String> failureMsgs) {
        if (this.status != OrderStatus.APPROVED)
            throw new OrderDomainException("order is not in the valid state for resturant to refuse the order");
        this.status = OrderStatus.CANCELLING;
        // report failure msgs
        if (failureMsgs != null) this.failureMsgs.addAll(failureMsgs.stream().filter(msg -> !msg.isBlank()).toList());

    }

    /**
     * the order service might publish an event when the order is PENDING and ready for payment to payment service, and the payment might faild
     * so the order-service will receive the response from payment service with failure and will change from PENDING -> CANCELLED
     * <p>
     * the order service might publish an event to the resturent service after payment is done, but resturent refuse so the order service
     * then the order will receive the response from resturent and after chaning the status from PAID -> CANCELLING, then the order service will
     * notify the payment to rollback the payment and based on the response from the payment the order will set from CANCELLING -> CANCELLED
     */
    public void cancel(List<String> failureMsgs) {
        if (this.status != OrderStatus.CANCELLING && this.status != OrderStatus.PENDING)
            throw new OrderDomainException("order is not in the valid state to be cancelled !");
        this.status = OrderStatus.CANCELLED;
        // report failure msgs
        if(failureMsgs != null)
            this.failureMsgs.addAll(failureMsgs.stream().filter(msg -> !msg.isBlank()).toList());
    }
}
