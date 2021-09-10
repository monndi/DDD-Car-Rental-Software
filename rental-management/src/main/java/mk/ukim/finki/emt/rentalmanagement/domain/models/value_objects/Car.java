package mk.ukim.finki.emt.rentalmanagement.domain.models.value_objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.base.ValueObject;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;
import mk.ukim.finki.emt.sharedkernel.domain.valueobjects.CarState;
import mk.ukim.finki.emt.sharedkernel.domain.valueobjects.CarStatus;

@Getter
public class Car implements ValueObject {
    private final Money price;
    private final CarId id;
    private final CarState carState;
    private final CarStatus carStatus;

    private Car() {
        this.id = CarId.randomId(CarId.class);
        this.price = null;
        this.carState = null;
        this.carStatus = null;
    }
    @JsonCreator
    public Car(@JsonProperty("id")CarId id,
               @JsonProperty("carState")CarState carState,
               @JsonProperty("carStatus")CarStatus carStatus,
               @JsonProperty("carPrice") Money price) {
        this.price = price;
        this.id = id;
        this.carState = carState;
        this.carStatus = carStatus;
    }
}
