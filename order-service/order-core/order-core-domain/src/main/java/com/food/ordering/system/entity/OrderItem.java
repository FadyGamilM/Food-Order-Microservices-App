package com.food.ordering.system.entity;

import com.food.ordering.system.domain.entity.BaseEntity;
import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.domain.valueobject.OrderID;
import com.food.ordering.system.valueobject.OrderItemID;
import com.food.ordering.system.valueobject.OrderItemQuantity;

public class OrderItem extends BaseEntity<OrderItemID> {

    // the orderId will be assigned later through the business logic, so we need it to be mutable and assignable
    private OrderID orderID;

    // the OrderItem is a wrapper above the Product, its the Product in the context of order service
    private final Product product;

    // the quantity of this product
    private final OrderItemQuantity quantity;

    private final Money price;

    // subTotal = quantity * price
    private final Money subTotal;

    public OrderID getOrderID() {
        return orderID;
    }

    public OrderItemQuantity getQuantity() {
        return quantity;
    }

    public Money getPrice() {
        return price;
    }

    public Money getSubTotal() {
        return subTotal;
    }

    public Product getProduct() {
        return product;
    }


    // private constructor to be used inside the builder pattern (via the build method_
    private OrderItem(Builder builder) {
        this.product = builder.product;
        this.quantity = builder.quantity;
        this.price = builder.price;
        this.subTotal = builder.subTotal;
    }

    // the Builder class itself
    public static class Builder {
        public Builder() {
        }

        private Money price;
        private Money subTotal;
        OrderItemQuantity quantity;
        Product product;

        public Builder price(Money val) {
            this.price = val;
            return this;
        }

        public Builder subTotal(Money val) {
            this.subTotal = val;
            return this;
        }

        public Builder quantity(OrderItemQuantity val) {
            this.quantity = val;
            return this;
        }

        public Builder product(Product val) {
            this.product = val;
            return this;
        }

        // the build method that returns the entity by calling the entity's private constructor and pass the builder
        public OrderItem build() {
            return new OrderItem(this);
        }

    }

}
