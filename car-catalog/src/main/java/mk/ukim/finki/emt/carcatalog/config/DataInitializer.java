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
        CarType c = new CarType("Audi", "A6 Sedan", "2021",261, 3000,new BodyType(4, true), "Premium unleaded (required)",   "https://img.drivemag.net/jato_car_photos/AUDI%2FA6%2Fsedan%2F4%2F2018%2Fus%2Fexterior-photos%2Fo%2FNEW%20AUDI%20A6%20SEDAN%204%20DOOR%20C6%2050TDI%20HYBRID%20QUATTRO%203.0%20TFSI%202018%2010.jpg", new Money(Currency.USD,50));
        c.addNewCar(new CarState(State.PERFECT), new CarStatus(Status.FREE));
        c.addNewCar(new CarState(State.GOOD), new CarStatus(Status.FREE));
        c.addNewCar(new CarState(State.PERFECT), new CarStatus(Status.RENTED));
        c.addNewCar(new CarState(State.GOOD), new CarStatus(Status.FREE));
        c.addNewCar(new CarState(State.BAD), new CarStatus(Status.FREE));

        CarType c1 = new CarType("Mercedes-Benz", "C 300 Sedan", "2021",255, 2000,new BodyType(4, true), "Premium unleaded (required)", "https://cars.usnews.com/static/images/Auto/izmo/i100899632/2019_mercedes_benz_c_class_angularfront.jpg", new Money(Currency.USD,65));
        c1.addNewCar(new CarState(State.PERFECT), new CarStatus(Status.FREE));
        c1.addNewCar(new CarState(State.GOOD), new CarStatus(Status.FREE));
        c1.addNewCar( new CarState(State.PERFECT), new CarStatus(Status.RENTED));
        c1.addNewCar(new CarState(State.GOOD), new CarStatus(Status.FREE));
        c1.addNewCar(new CarState(State.BAD), new CarStatus(Status.FREE));

        if (carTypeRepository.findAll().isEmpty()) {
            carTypeRepository.saveAll(Arrays.asList(c, c1));
        }
    }
}
