package com.food.ordering.system.entity;

import com.food.ordering.system.domain.entity.BaseEntity;
import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.domain.valueobject.ProductID;

// its an entity, so it must be compared using the identifier, and we laverage this via the BaseEntity
public class Product extends BaseEntity<ProductID> {
    private String productName;
    private Money productPrice;

    public Product(String name, Money price) {
        this.productName = name;
        this.productPrice = price;
    }

    public void setProductNameAndPrice(String name, Money price) {
        this.productName = name;
        this.productPrice = price;
    }

    public Money getProductPrice() {
        return productPrice;
    }

    public String getProductName() {
        return productName;
    }
}
