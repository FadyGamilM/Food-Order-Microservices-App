package com.food.ordering.system.domain.entity;

import java.util.Objects;

// i will extend this BaseEntity class into all other entites in different domains, and replace the generic ID type with the
// custom value object identifier type of each domain entity
public abstract class BaseEntity<ID> {
    private ID id;

    // getters and setters

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    // essential methods to compare two entities togeather

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntity<?> that = (BaseEntity<?>) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
