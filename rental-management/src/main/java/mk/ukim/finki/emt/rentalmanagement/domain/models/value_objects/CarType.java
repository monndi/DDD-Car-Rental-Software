package mk.ukim.finki.emt.rentalmanagement.domain.models.value_objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.base.ValueObject;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;
import mk.ukim.finki.emt.sharedkernel.domain.valueobjects.BodyType;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
public class CarType implements ValueObject {
    private final CarTypeId id;
    private final String carBrand;
    private final String carName;
    private final String year;
    private final double horsePower;
    private final double engineCapacity;
    private final BodyType bodyType;
    private final String fuelType;
    private final Money price;
    private final String imgUrl;

    private final List<Car> carList;

    private CarType() {
        this.id = CarTypeId.randomId(CarTypeId.class);
        this.carBrand = null;
        this.carName = null;
        this.year = null;
        this.horsePower = 0;
        this.engineCapacity = 0;
        this.bodyType = null;
        this.fuelType = null;
        this.carList = new ArrayList<>();
        this.price = null;
        this.imgUrl = null;
    }
    @JsonCreator
    public CarType(@JsonProperty("id") CarTypeId id,
                   @JsonProperty("carBrand")String carBrand,
                   @JsonProperty("carName") String carName,
                   @JsonProperty("year")String year,
                   @JsonProperty("horsePower")double horsePower,
                   @JsonProperty("engineCapacity")double engineCapacity,
                   @JsonProperty("bodyType")BodyType bodyType,
                   @JsonProperty("fuelType")String fuelType,
                   @JsonProperty("price")Money price,
                   @JsonProperty("imgUrl")String imgUrl) {
        this.id = id;
        this.carBrand = carBrand;
        this.carName = carName;
        this.year = year;
        this.horsePower = horsePower;
        this.engineCapacity = engineCapacity;
        this.bodyType = bodyType;
        this.fuelType = fuelType;
        this.carList = new ArrayList<>();
        this.price = price;
        this.imgUrl = imgUrl;
    }
}
