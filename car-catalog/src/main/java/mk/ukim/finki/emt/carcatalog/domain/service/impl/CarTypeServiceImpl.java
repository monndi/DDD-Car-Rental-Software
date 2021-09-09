package mk.ukim.finki.emt.carcatalog.domain.service.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.carcatalog.domain.exceptions.CarIdNotExistException;
import mk.ukim.finki.emt.carcatalog.domain.exceptions.CarTypeIdNotExistException;
import mk.ukim.finki.emt.carcatalog.domain.models.CarId;
import mk.ukim.finki.emt.carcatalog.domain.models.CarType;
import mk.ukim.finki.emt.carcatalog.domain.models.CarTypeId;
import mk.ukim.finki.emt.carcatalog.domain.models.value_object.CarStatus;
import mk.ukim.finki.emt.carcatalog.domain.repository.CarTypeRepository;
import mk.ukim.finki.emt.carcatalog.domain.service.CarTypeService;
import mk.ukim.finki.emt.carcatalog.domain.service.forms.CarForm;
import mk.ukim.finki.emt.carcatalog.domain.service.forms.CarTypeForm;
import mk.ukim.finki.emt.sharedkernel.domain.valueobjects.CarState;
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
    public CarTypeId importCarType(CarTypeForm carTypeForm) {
        Objects.requireNonNull(carTypeForm,  "car type form must not be null");
        var constraintViolations = validator.validate(carTypeForm);
        if (constraintViolations.size() > 0) {
            throw new ConstraintViolationException("The car type form is not valid", constraintViolations);
        }
        var newCarType = carTypeRepository.saveAndFlush(toDomainObject(carTypeForm));
        return newCarType.getId();
    }

    private CarType toDomainObject(CarTypeForm carTypeForm) {
        var carType= new CarType(carTypeForm.getCarBrand(), carTypeForm.getCarName(),
                carTypeForm.getYear(), carTypeForm.getHorsePower(), carTypeForm.getEngineCapacity(), carTypeForm.getBodyType(), carTypeForm.getFuelType());
        carTypeForm.getCars().forEach(car->carType.addNewCar(car.getCarPrice(), car.getCarState(), car.getCarStatus()));
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
    public void addCar(CarTypeId carTypeId, CarForm carForm) throws CarIdNotExistException {
        CarType carType = carTypeRepository.findById(carTypeId).orElseThrow(CarTypeIdNotExistException::new);
        carType.addNewCar(carForm.getPrice(),carForm.getCarState(), carForm.getCarStatus());
        carTypeRepository.saveAndFlush(carType);
    }

    @Override
    public void deleteCar(CarTypeId carTypeId, CarId carId) throws CarTypeIdNotExistException, CarIdNotExistException {
        CarType carType = carTypeRepository.findById(carTypeId).orElseThrow(CarTypeIdNotExistException::new);
        carType.removeCar(carId);
        carTypeRepository.saveAndFlush(carType);
    }

    @Override
    public void rentCar(CarTypeId carTypeId, CarId carId) throws CarTypeIdNotExistException, CarIdNotExistException {
        var carType = this.carTypeRepository.findById(carTypeId).orElseThrow(CarTypeIdNotExistException::new);
        carType.rentCar(carId);
        this.carTypeRepository.saveAndFlush(carType);
    }

    @Override
    public void returnCar(CarTypeId carTypeId, CarId carId, CarState returnState) throws CarTypeIdNotExistException, CarIdNotExistException {
        var carType = this.carTypeRepository.findById(carTypeId).orElseThrow(CarTypeIdNotExistException::new);
        carType.returnCar(carId, returnState);
        this.carTypeRepository.saveAndFlush(carType);
    }
}
