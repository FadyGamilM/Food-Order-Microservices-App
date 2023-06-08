package com.food.ordering.system.entity;

import com.food.ordering.system.domain.entity.AggregateRoot;
import com.food.ordering.system.domain.valueobject.ResturantID;

import java.util.List;

public class Resturant extends AggregateRoot<ResturantID> {
    private boolean isActive = true; // by default the resturant is active until the user de-activate it
    // resturant needs to keep track of all the avaialble products

    // its marked as final because it will be set at the initialization time, i will use builder pattern
    private final List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    public boolean isResturantActive() {
        return this.isActive;
    }


    private Resturant(Builder builder) {
        setId(builder.id);
        this.isActive = builder.isActive;
        this.products = builder.products;
    }

    public static class Builder {
        private ResturantID id;

        public Builder() {
        }

        private boolean isActive;
        private List<Product> products;

        public Builder id(ResturantID val) {
            this.id = id;
            return this;
        }

        public Builder isActive(boolean val) {
            this.isActive = val;
            return this;
        }

        public Builder products(List<Product> val) {
            this.products = val;
            return this;
        }

        public Resturant build() {
            return new Resturant(this);
        }
    }
}
