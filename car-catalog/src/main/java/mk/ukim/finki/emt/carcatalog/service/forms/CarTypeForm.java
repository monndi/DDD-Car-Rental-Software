package mk.ukim.finki.emt.carcatalog.service.forms;

import lombok.Data;
import mk.ukim.finki.emt.carcatalog.domain.models.Car;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;
import mk.ukim.finki.emt.sharedkernel.domain.valueobjects.BodyType;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
public class CarTypeForm {
    @NotNull
    private String carBrand;
    @NotNull
    private String carName;
    @NotNull
    private String year;
    @NotNull
    private double horsePower;
    @NotNull
    private double engineCapacity;
    @NotNull
    private BodyType bodyType;
    @NotNull
    private String fuelType;
    @NotNull
    private String imgUrl;
    @NotNull
    private String currency;
    @NotNull double amount;

    private List<Car> cars = new ArrayList<>();
}
