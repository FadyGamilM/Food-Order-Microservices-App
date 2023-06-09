package com.food.ordering.system.entity;

import com.food.ordering.system.domain.entity.BaseEntity;
import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.domain.valueobject.OrderID;
import com.food.ordering.system.exception.OrderDomainException;
import com.food.ordering.system.valueobject.OrderItemID;
import com.food.ordering.system.valueobject.OrderItemQuantity;

import java.math.BigDecimal;

public class OrderItem extends BaseEntity<OrderItemID> {
    // private constructor to be used inside the builder pattern (via the build method_
    private OrderItem(Builder builder) {
        setId(builder.orderItemID);
        this.product = builder.product;
        this.quantity = builder.quantity;
        this.price = builder.price;
        this.subTotal = builder.subTotal;
    }

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


    // ? : implementation of the builder pattern
    public static class Builder {
        public Builder() {
        }

        private OrderItemID orderItemID;
        private Money price;
        private Money subTotal;
        private OrderItemQuantity quantity;
        private Product product;

        public Builder orderItemID(OrderItemID val) {
            this.orderItemID = val;
            return this;
        }

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

    // ? : domain logic methods
    // * initialize order items by defining the identifier and the related order via its identifier
    void initializeOrderItem(OrderID orderID, OrderItemID orderItemID) {
        this.orderID = orderID;
        this.setId(orderItemID);
    }

    /**
     * compares the price of the orderItem to be the same as the matching product
     * compares the price to be a valid money object with valid amount ( > 0 )
     * compares the total Order-item price to be = quantity * product-price
     *
     * @return true if all these validations are true0
     */
    boolean validateOrderItemPrice() {
        return this.price.IsValidAmount() // validate that the price of the order item is greater than 0
                && this.price.MultiplyWithFactor(this.quantity.getQty()).getAmount().compareTo(this.subTotal.getAmount()) == 0 // validate that the subTotal = price_of_the_item * selected_qty
                && this.price.equals(this.product.getProductPrice()); // finally validate that the price of the order item = the price of the wrapped product
    }


}
