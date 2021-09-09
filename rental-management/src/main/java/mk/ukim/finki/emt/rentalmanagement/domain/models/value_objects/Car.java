package mk.ukim.finki.emt.rentalmanagement.domain.models.value_objects;

import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.base.ValueObject;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;

@Getter
public class Car implements ValueObject {
    private final Money price;
    private final CarId carId;

    private Car() {
        this.price = null;
        this.carId = null;
    }
    public Car(Money price, CarId carId) {
        this.price = price;
        this.carId = carId;
    }
}
