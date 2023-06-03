package com.food.ordering.system.domain.valueobject;

import java.util.Objects;

public abstract class BaseID<T> {
    private final T value;
    protected BaseID(T val){
        this.value = val;
    }

    public T getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseID<?> baseID = (BaseID<?>) o;
        return Objects.equals(getValue(), baseID.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
