package mk.ukim.finki.emt.carcatalog.domain.models;

import lombok.Getter;
import lombok.NonNull;
import mk.ukim.finki.emt.sharedkernel.domain.valueobjects.CarState;
import mk.ukim.finki.emt.sharedkernel.domain.valueobjects.CarStatus;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;

import javax.persistence.*;

@Entity
@Getter
@Table(name="car")
public class Car extends AbstractEntity<CarId> {

    private CarState carState;
    private CarStatus carStatus;

    @AttributeOverrides({@AttributeOverride(name = "amount", column =
    @Column(name = "price")), @AttributeOverride(name = "currency", column =
    @Column(name = "price_currency"))})
    private Money carPrice;

    private Car() {
        super(CarId.randomId(CarId.class));
    }


    // Aggregate in Bounded Context 1. Containing the necessary information about a car.
    public Car(@NonNull Money carPrice, @NonNull CarState carState,  @NonNull CarStatus carStatus) {
        super(CarId.randomId(CarId.class));
        this.carState = carState;
        this.carPrice = carPrice;
        this.carStatus = carStatus;
    }

    public void setCarStatus(CarStatus carStatus) {
        this.carStatus = carStatus;
    }
    public void setCarState(CarState carState) {
        this.carState = carState;
    }

    public void setCarPrice(Money money) {
        this.carPrice = money;
    }
}
