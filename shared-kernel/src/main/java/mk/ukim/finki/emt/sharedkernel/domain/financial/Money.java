package mk.ukim.finki.emt.sharedkernel.domain.financial;

import lombok.Getter;
import lombok.NonNull;
import mk.ukim.finki.emt.sharedkernel.domain.base.ValueObject;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Objects;

@Embeddable
@Getter
public class Money implements ValueObject {
    @Enumerated(value = EnumType.STRING)
    private final Currency currency;
    private final double amount;

    protected Money() {
        this.currency = null;
        this.amount = 0.0;
    }
    // Shared Value Object
    public Money(@NonNull Currency currency,@NonNull double amount) {
        this.currency = currency;
        this.amount = amount;
    }

    public static Money valueOf(Currency currency, double amount) { return new Money(currency, amount); }

    public Money add(Money money) {
        if(!currency.equals(money.currency)) {
            throw new IllegalArgumentException("Cannot add two Money object with different currency!");
        }
        return new Money(currency, amount + money.amount);
    }

    public Money substract(Money money) {
        if(!currency.equals(money.currency)) {
            throw new IllegalArgumentException("Cannot substract two Money object with different currency!");
        }
        return new Money(currency, amount - money.amount);
    }
    public Money multiply(double a) { return new Money(currency, amount*a); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return Double.compare(money.amount, amount) == 0 &&
                currency == money.currency;
    }

    @Override
    public int hashCode() {
        return Objects.hash(currency, amount);
    }
}
