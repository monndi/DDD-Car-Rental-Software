package mk.ukim.finki.emt.carcatalog.config;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.carcatalog.domain.models.CarType;
import mk.ukim.finki.emt.sharedkernel.domain.valueobjects.CarStatus;
import mk.ukim.finki.emt.sharedkernel.domain.valueobjects.Status;
import mk.ukim.finki.emt.carcatalog.domain.repository.CarTypeRepository;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Currency;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;
import mk.ukim.finki.emt.sharedkernel.domain.valueobjects.BodyType;
import mk.ukim.finki.emt.sharedkernel.domain.valueobjects.CarState;
import mk.ukim.finki.emt.sharedkernel.domain.valueobjects.State;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Component
@AllArgsConstructor
public class DataInitializer {
    private final CarTypeRepository carTypeRepository;

    @PostConstruct
    public void initData() {
        CarType c = new CarType("Audi", "A6 Sedan", "2021",261, 3000,new BodyType(4, true), "Premium unleaded (required)");
        c.addNewCar(new Money(Currency.USD,49800), new CarState(State.PERFECT), new CarStatus(Status.FREE));
        c.addNewCar(new Money(Currency.USD,49500), new CarState(State.GOOD), new CarStatus(Status.FREE));
        c.addNewCar(new Money(Currency.USD,49800), new CarState(State.PERFECT), new CarStatus(Status.RENTED));
        c.addNewCar(new Money(Currency.USD,49000), new CarState(State.GOOD), new CarStatus(Status.FREE));
        c.addNewCar(new Money(Currency.USD,49000), new CarState(State.BAD), new CarStatus(Status.FREE));

        CarType c1 = new CarType("Mercedes-Benz", "C 300 Sedan", "2021",255, 2000,new BodyType(4, true), "Premium unleaded (required)");
        c1.addNewCar(new Money(Currency.USD,41600), new CarState(State.PERFECT), new CarStatus(Status.FREE));
        c1.addNewCar(new Money(Currency.USD,41000), new CarState(State.GOOD), new CarStatus(Status.FREE));
        c1.addNewCar(new Money(Currency.USD,41600), new CarState(State.PERFECT), new CarStatus(Status.RENTED));
        c1.addNewCar(new Money(Currency.USD,41000), new CarState(State.GOOD), new CarStatus(Status.FREE));
        c1.addNewCar(new Money(Currency.USD,41600), new CarState(State.BAD), new CarStatus(Status.FREE));

        if (carTypeRepository.findAll().isEmpty()) {
            carTypeRepository.saveAll(Arrays.asList(c, c1));
        }
    }
}
