package com.food.ordering.system.entity;

import com.food.ordering.system.domain.entity.AggregateRoot;
import com.food.ordering.system.domain.valueobject.CustomerID;
import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.domain.valueobject.OrderID;
import com.food.ordering.system.domain.valueobject.ResturantID;
import com.food.ordering.system.valueobject.StreetAddress;
import com.food.ordering.system.valueobject.TrackingID;

import java.util.ArrayList;
import java.util.List;

public class Order extends AggregateRoot<OrderID> {

    private final CustomerID customerId;
    private final ResturantID resturantID;
    private final StreetAddress streetAddress;

    //    used to track the order and i use this field to not use the PK in our application logic
    private final TrackingID trackingId;

    private final Money totalPrice;

    // each order has a lsit of order items
    private List<OrderItem> orderItems = new ArrayList<OrderItem>();

}
