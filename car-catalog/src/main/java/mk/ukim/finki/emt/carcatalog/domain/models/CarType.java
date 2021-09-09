package mk.ukim.finki.emt.carcatalog.domain.models;

import lombok.NonNull;
import mk.ukim.finki.emt.carcatalog.domain.exceptions.CarIdNotExistException;
import mk.ukim.finki.emt.carcatalog.domain.models.value_object.*;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.sharedkernel.domain.valueobjects.BodyType;
import mk.ukim.finki.emt.sharedkernel.domain.valueobjects.CarState;
import mk.ukim.finki.emt.sharedkernel.domain.valueobjects.State;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

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

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Car> carList;
//    @AttributeOverrides({@AttributeOverride(name = "amount", column =
//    @Column(name = "price")), @AttributeOverride(name = "currency", column =
//    @Column(name = "price_currency"))})
//    private Money price;

    private CarType() {
        super(CarTypeId.randomId(CarTypeId.class));
        this.carList = new HashSet<>();
    }
    public CarType(String carBrand, String carName, String year, double horsePower,double engineCapacity, BodyType bodyType, String fuelType) {
        super(CarTypeId.randomId(CarTypeId.class));
        this.carBrand = carBrand;
        this.carName = carName;
        this.year = year;
        this.horsePower = horsePower;
        this.engineCapacity = engineCapacity;
        this.bodyType = bodyType;
        this.fuelType = fuelType;
        this.carList = new HashSet<>();
    }


    public List<Car> getAvailableCars() {
        return carList.stream()
                .filter(x->x.getCarStatus().getStatus() == Status.FREE && x.getCarState().getState() != State.BAD)
                .collect(Collectors.toList());
    }

    public Car addNewCar(@NonNull Money carPrice, @NonNull CarState carState, @NonNull CarStatus carStatus) {
        var item = new Car(carPrice, carState, carStatus);
        carList.add(item);
        return item;
    }
    public void removeCar(@NonNull CarId carId) {
        Objects.requireNonNull(carId, "car id must not be null");
        carList.removeIf(c-> c.getId().equals(carId));
    }

    public void returnCar(CarId carId, CarState returnState) {
        var car = carList.stream().filter(x->x.getId().equals(carId)).findAny().orElseThrow(CarIdNotExistException::new);
        car.setCarState(returnState);
        car.setCarStatus(new CarStatus(Status.FREE));
    }

    public void rentCar(CarId carId) {
        var car = carList.stream().filter(x->x.getId().equals(carId)).findAny().orElseThrow(CarIdNotExistException::new);
        car.setCarStatus(new CarStatus(Status.RENTED));
    }

}
