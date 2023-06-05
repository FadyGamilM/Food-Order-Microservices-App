package com.food.ordering.system.valueobject;

import com.food.ordering.system.domain.valueobject.BaseID;

import java.util.Objects;
import java.util.UUID;

// if the streetAddress had a problem with data-access layer impl, just remove the inheritence relation and give this entity an UUID id field and define its getter
public class StreetAddress extends BaseID<UUID> {
    String streetName;
    String postalCode;
    String city;

    protected StreetAddress(UUID val, String street, String postal, String city) {
        super(val);
        this.streetName = street;
        this.postalCode = postal;
        this.city = city;
    }

    //    value objects are comparable based on all atributes without identfier ..
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        StreetAddress that = (StreetAddress) o;
        return Objects.equals(streetName, that.streetName) && Objects.equals(postalCode, that.postalCode) && Objects.equals(city, that.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), streetName, postalCode, city);
    }
}
