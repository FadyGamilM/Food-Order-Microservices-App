package com.food.ordering.system.entity;

import com.food.ordering.system.domain.entity.AggregateRoot;
import com.food.ordering.system.domain.valueobject.*;
import com.food.ordering.system.valueobject.StreetAddress;
import com.food.ordering.system.valueobject.TrackingID;

import javax.sound.midi.Track;
import java.util.ArrayList;
import java.util.List;

public class Order extends AggregateRoot<OrderID> {

    public static class Builder {
        private OrderID orderID;
        private CustomerID customerID;
        private ResturantID resturantID;
        private TrackingID trackingID;
        private Money totalPrice;
        private List<OrderItem> orderItems;
        private List<String> failureMsgs;
        private OrderStatus status;
        private StreetAddress streetAddress;

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

        public Builder streetAddress(StreetAddress val) {
            this.streetAddress = val;
            return this;
        }


        public Order build() {
            return new Order(this);
        }
    }

    private Order(Builder builder) {
        super.setId(builder.orderID);
        this.customerId = builder.customerID;
        this.resturantID = builder.resturantID;
        this.trackingId = builder.trackingID;
        this.orderItems = builder.orderItems;
        this.totalPrice = builder.totalPrice;
        this.failureMsgs = builder.failureMsgs;
        this.status = builder.status;
        this.streetAddress = builder.streetAddress;
    }

    private final CustomerID customerId;
    private final ResturantID resturantID;
    private final StreetAddress streetAddress;

    //    used to track the order and i use this field to not use the PK in our application logic
    private final TrackingID trackingId;

    private final Money totalPrice;

    // each order has a lsit of order items
    private final List<OrderItem> orderItems;

    private OrderStatus status; // not final because it will change through the life-cycle of the app

    private List<String> failureMsgs;

}
