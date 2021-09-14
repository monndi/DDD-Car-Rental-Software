package mk.ukim.finki.emt.sharedkernel.domain.dto;

import lombok.Data;
import mk.ukim.finki.emt.sharedkernel.domain.valueobjects.*;

import java.util.ArrayList;
import java.util.List;

@Data
public class CarTypeDto {
    private String carBrand;
    private String carName;
    private String year;
    private double horsePower;
    private double engineCapacity;
    private BodyType bodyType;
    private String fuelType;
    private List<CarDto> carList;

    public CarTypeDto() {}

    public CarTypeDto(String carBrand, String carName, String year, double horsePower,double engineCapacity, BodyType bodyType, String fuelType, List<CarDto> carList) {
        this.carBrand = carBrand;
        this.carName = carName;
        this.year = year;
        this.horsePower = horsePower;
        this.engineCapacity = engineCapacity;
        this.bodyType = bodyType;
        this.fuelType = fuelType;
        this.carList = new ArrayList<>();
        this.carList.addAll(carList);
    }
}
