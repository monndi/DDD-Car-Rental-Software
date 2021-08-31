package mk.ukim.finki.emt.rentalmanagement.domain.models;

import mk.ukim.finki.emt.rentalmanagement.domain.models.value_objects.CarState;
import mk.ukim.finki.emt.rentalmanagement.domain.models.value_objects.CarStatus;
import mk.ukim.finki.emt.rentalmanagement.domain.models.value_objects.CarTypeId;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="car")
public class Car extends AbstractEntity<CarId> {

    private CarState carState;
    private CarStatus carStatus;
    private Money carPrice;

    @AttributeOverride(name="id", column = @Column(name="car_type_id", nullable = false))
    private CarTypeId carTypeId;

}
