package mk.ukim.finki.emt.carcatalog.domain.models;

import lombok.Getter;
import lombok.NonNull;
import mk.ukim.finki.emt.carcatalog.domain.exceptions.CarIdNotExistException;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.sharedkernel.domain.valueobjects.*;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name="car_type")
@Getter
public class CarType extends AbstractEntity<CarTypeId> {
    private String carBrand;
    private String carName;
    private String year;
    private double horsePower;
    private double engineCapacity;
    private BodyType bodyType;
    private String fuelType;
    private String imgUrl;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Car> carList;
    @AttributeOverrides({@AttributeOverride(name = "amount", column =
    @Column(name = "price")), @AttributeOverride(name = "currency", column =
    @Column(name = "price_currency"))})
    private Money price;

    private CarType() {
        super(CarTypeId.randomId(CarTypeId.class));
        this.carList = new ArrayList<>();
    }
    // CarType - Aggregate Root for Bounded Context 1, implementing methods for business logic for all carTypes and Cars, communicating with Bounded Context 2.
    public CarType(String carBrand, String carName, String year, double horsePower,double engineCapacity, BodyType bodyType, String fuelType, String imgUrl, Money money) {
        super(CarTypeId.randomId(CarTypeId.class));
        this.carBrand = carBrand;
        this.carName = carName;
        this.year = year;
        this.horsePower = horsePower;
        this.engineCapacity = engineCapacity;
        this.bodyType = bodyType;
        this.fuelType = fuelType;
        this.carList = new ArrayList<>();
        this.imgUrl = imgUrl;
        this.price = money;
    }

    // Get only the available, with status : "free" and state: "good" or "perfect"
    public List<Car> getAvailableCars() {
        return carList.stream()
                .filter(x->x.getCarStatus().getStatus() == Status.FREE && x.getCarState().getState() != State.BAD)
                .collect(Collectors.toList());
    }

    // Add new car to the carList
    public Car addNewCar(@NonNull CarState carState, @NonNull CarStatus carStatus) {
        var item = new Car(calculatePrice(price, carState), carState, carStatus);
        carList.add(item);
        return item;
    }
    // Calculate the price of the car depending on the state of the car
    private Money calculatePrice(Money carPrice, CarState carState) {
        double amount = carPrice.getAmount();
        if (carState.getState().equals(State.BAD)) {
            amount = 0;
        } else if (carState.getState().equals(State.GOOD)) {
            amount -= amount * 0.30;
        }
        return new Money(carPrice.getCurrency(), amount);
    }
    // Remove a car from the list
    public void removeCar(@NonNull CarId carId) {
        Objects.requireNonNull(carId, "car id must not be null");
        carList.removeIf(c-> c.getId().equals(carId));
    }

    // Change the status and the state of a car when it is returned
    public void returnCar(CarId carId, CarState returnState) {
        var car = carList.stream().filter(x->x.getId().getId().equals(carId.getId())).findAny().orElseThrow(CarIdNotExistException::new);
        car.setCarState(returnState);
        car.setCarPrice(calculatePrice(this.price, returnState));
        car.setCarStatus(new CarStatus(Status.FREE));
    }

    // Changes the status of the car when it is rented
    public void rentCar(CarId carId) {
        var car = carList.stream().filter(x->x.getId().getId().equals(carId.getId())).findAny().orElseThrow(CarIdNotExistException::new);
        car.setCarStatus(new CarStatus(Status.RENTED));
    }

}
