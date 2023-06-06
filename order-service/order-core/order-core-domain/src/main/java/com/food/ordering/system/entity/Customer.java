package com.food.ordering.system.entity;

import com.food.ordering.system.domain.entity.AggregateRoot;
import com.food.ordering.system.domain.valueobject.CustomerID;

// the id in the customer domain aggregate in the logic of order-service is just used to check if this user exists or not before using it in any business-logic related to order-service
public class Customer extends AggregateRoot<CustomerID> {
}
