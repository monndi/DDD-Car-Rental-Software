package mk.ukim.finki.emt.carcatalog.domain.models;

import mk.ukim.finki.emt.carcatalog.domain.models.value_object.BodyType;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;

import javax.persistence.*;

@Entity
@Table(name="car_type")
public class CarType extends AbstractEntity<CarTypeId> {
    private String carBrand;
    private String carName;
    private String year;
    private double horsePower;
    private double engineCapacity;
    private BodyType bodyType;
    private String fuelType;
    private int numberOfAvailableCars;
//    @AttributeOverrides({@AttributeOverride(name = "amount", column =
//    @Column(name = "price")), @AttributeOverride(name = "currency", column =
//    @Column(name = "price_currency"))})
//    private Money price;
}
