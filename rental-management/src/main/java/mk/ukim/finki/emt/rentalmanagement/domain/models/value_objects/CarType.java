package mk.ukim.finki.emt.rentalmanagement.domain.models.value_objects;

import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.base.ValueObject;
import mk.ukim.finki.emt.sharedkernel.domain.valueobjects.BodyType;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Getter
public class CarType implements ValueObject {
    private String carBrand;
    private String carName;
    private String year;
    private double horsePower;
    private double engineCapacity;
    private BodyType bodyType;
    private String fuelType;

    private Set<Car> carList;

    private CarType() {
        this.carBrand = null;
        this.carName = null;
        this.year = null;
        this.horsePower = 0;
        this.engineCapacity = 0;
        this.bodyType = null;
        this.fuelType = null;
        this.carList = new HashSet<>();
    }
    public CarType(String carBrand, String carName, String year, double horsePower, double engineCapacity, BodyType bodyType, String fuelType) {
        this.carBrand = carBrand;
        this.carName = carName;
        this.year = year;
        this.horsePower = horsePower;
        this.engineCapacity = engineCapacity;
        this.bodyType = bodyType;
        this.fuelType = fuelType;
        this.carList = new HashSet<>();
    }
}
