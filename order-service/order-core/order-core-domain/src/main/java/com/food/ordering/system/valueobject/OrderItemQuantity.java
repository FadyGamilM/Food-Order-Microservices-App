package com.food.ordering.system.valueobject;

public class OrderItemQuantity {
    private final Integer qty;

    public OrderItemQuantity(Integer val) {
        if (val > 0)
            qty = val;
        else qty = 1;
    }

    public Integer getQty() {
        return qty;
    }
}
