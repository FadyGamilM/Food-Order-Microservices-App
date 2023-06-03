package com.food.ordering.system.domain.valueobject;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Money {
    private final BigDecimal amount;

    public Money(BigDecimal val) {
        amount = val;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * @return true if the money amount is not zero and not null.
     */
    public boolean IsValidAmount() {
        if (this.amount == null || this.amount.compareTo(BigDecimal.ZERO) <= 0) return false;
        return true;
    }

    /**
     * @param val : receives a BigDecimal value
     * @return : boolean value, ture if Money is > passed parameter
     */
    public boolean IsGreaterThan(BigDecimal val) {
        if (this.IsValidAmount() && this.getAmount().compareTo(val) > 0) return true;
        return false;
    }

    /**
     * @param val : receives a BigDecimal value
     * @return : boolean value, ture if Money is < passed parameter
     */
    public boolean IsLessThan(BigDecimal val) {
        if (this.IsValidAmount() && this.getAmount().compareTo(val) > 0) return true;
        return false;
    }

    /**
     * @param val : the BigDecimal value to be scaled
     * @return scaled BigDecimal value with 2 numbers after decimal point and rounded with half even rounding mode (banker mode)
     */
    private BigDecimal SetScale(BigDecimal val) {
        return val.setScale(2, RoundingMode.HALF_EVEN); // have even rounding mode is the mode that is used in banking operations
    }

    /**
     * @param val
     * @return Money value object after adding the value of `val` parameter to the old amount
     */
    public Money AddAmount(Money val) {
        return new Money(this.SetScale(this.getAmount().add(val.getAmount())));
    }

    /**
     * @param val
     * @return Money value object after subtracting the value of `val` parameter from the old amount
     */
    public Money SubtractAmount(Money val) {
        return new Money(this.SetScale(this.getAmount().subtract(val.getAmount())));
    }

    public Money MultiplyAmount(Money val) {
        return new Money(this.SetScale(this.getAmount().multiply(val.getAmount())));
    }

    public Money MultiplyWithFactor(int factor) {
        return new Money(this.SetScale(this.getAmount().multiply(new BigDecimal(factor))));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return Objects.equals(amount, money.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
