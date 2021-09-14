package mk.ukim.finki.emt.carcatalog.service.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.carcatalog.domain.exceptions.CarIdNotExistException;
import mk.ukim.finki.emt.carcatalog.domain.exceptions.CarTypeIdNotExistException;
import mk.ukim.finki.emt.carcatalog.domain.models.CarId;
import mk.ukim.finki.emt.carcatalog.domain.models.CarType;
import mk.ukim.finki.emt.carcatalog.domain.models.CarTypeId;
import mk.ukim.finki.emt.carcatalog.domain.repository.CarTypeRepository;
import mk.ukim.finki.emt.carcatalog.service.CarTypeService;
import mk.ukim.finki.emt.carcatalog.service.forms.CarForm;
import mk.ukim.finki.emt.carcatalog.service.forms.CarTypeForm;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Currency;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;
import mk.ukim.finki.emt.sharedkernel.domain.valueobjects.CarState;
import mk.ukim.finki.emt.sharedkernel.domain.valueobjects.CarStatus;
import mk.ukim.finki.emt.sharedkernel.domain.valueobjects.Status;
import mk.ukim.finki.emt.sharedkernel.domain.valueobjects.State;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class CarTypeServiceImpl implements CarTypeService {

    private final CarTypeRepository carTypeRepository;
    private  final Validator validator;

    @Override
    public Optional<CarTypeId> importCarType(CarTypeForm carTypeForm) {
        Objects.requireNonNull(carTypeForm,  "car type form must not be null");
        var constraintViolations = validator.validate(carTypeForm);
        if (constraintViolations.size() > 0) {
            throw new ConstraintViolationException("The car type form is not valid", constraintViolations);
        }
        var newCarType = carTypeRepository.saveAndFlush(toDomainObject(carTypeForm));
        return Optional.of(newCarType.getId());
    }

    private CarType toDomainObject(CarTypeForm carTypeForm) {
        var carType= new CarType(carTypeForm.getCarBrand(), carTypeForm.getCarName(),
                carTypeForm.getYear(), carTypeForm.getHorsePower(), carTypeForm.getEngineCapacity(), carTypeForm.getBodyType(), carTypeForm.getFuelType(), carTypeForm.getImgUrl(), new Money(Currency.valueOf(carTypeForm.getCurrency()), carTypeForm.getAmount()));
        carTypeForm.getCars().forEach(car->carType.addNewCar(car.getCarState(), car.getCarStatus()));
        return carType;
    }

    @Override
    public List<CarType> findAll() {
        return carTypeRepository.findAll();
    }

    @Override
    public Optional<CarType> findById(CarTypeId carTypeId) {
        return carTypeRepository.findById(carTypeId);
    }

    @Override
    @Transactional
    public Optional<CarType> addCar(CarTypeId carTypeId, CarForm carForm) throws CarIdNotExistException {
        CarType carType = carTypeRepository.findById(carTypeId).orElseThrow(CarTypeIdNotExistException::new);
        carType.addNewCar(new CarState(State.valueOf(carForm.getCarState())), new CarStatus(Status.valueOf(carForm.getCarStatus())));
        carTypeRepository.saveAndFlush(carType);
        return Optional.of(carType);
    }

    @Override
    public void deleteCar(CarTypeId carTypeId, CarId carId) throws CarTypeIdNotExistException, CarIdNotExistException {
        CarType carType = carTypeRepository.findById(carTypeId).orElseThrow(CarTypeIdNotExistException::new);
        carType.removeCar(carId);
        carTypeRepository.saveAndFlush(carType);
    }

    @Override
    @Transactional
    public void rentCar(CarTypeId carTypeId, CarId carId) throws CarTypeIdNotExistException, CarIdNotExistException {
        var carType = this.carTypeRepository.findById(carTypeId).orElseThrow(CarTypeIdNotExistException::new);
        carType.rentCar(carId);
        this.carTypeRepository.saveAndFlush(carType);
    }

    @Override
    @Transactional
    public void returnCar(CarTypeId carTypeId, CarId carId, CarState returnState) throws CarTypeIdNotExistException, CarIdNotExistException {
        var carType = this.carTypeRepository.findById(carTypeId).orElseThrow(CarTypeIdNotExistException::new);
        carType.returnCar(carId, returnState);
        this.carTypeRepository.saveAndFlush(carType);
    }
}
