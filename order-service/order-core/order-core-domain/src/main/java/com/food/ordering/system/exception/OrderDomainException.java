package com.food.ordering.system.exception;

import com.food.ordering.system.domain.exception.DomainException;

public class OrderDomainException extends DomainException {
    public OrderDomainException(String msg) {
        super(msg);
    }

    public OrderDomainException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
