package mk.ukim.finki.emt.carcatalog.domain.models;

import mk.ukim.finki.emt.carcatalog.domain.models.valueObject.BodyType;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="car_type")
public class CarType extends AbstractEntity<CarTypeId> {
    private String carBrand;
    private String carName;
    private String year;
    private Double horsePower;
    private Double engineCapacity;
    private BodyType bodyType;
    private String fuelType;
    private Integer numberOfAvailableCars;
    private Money price;
}
