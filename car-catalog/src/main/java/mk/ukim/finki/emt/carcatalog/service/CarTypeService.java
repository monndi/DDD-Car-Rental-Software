package mk.ukim.finki.emt.carcatalog.service;

import mk.ukim.finki.emt.carcatalog.domain.exceptions.CarIdNotExistException;
import mk.ukim.finki.emt.carcatalog.domain.exceptions.CarTypeIdNotExistException;
import mk.ukim.finki.emt.carcatalog.domain.models.CarId;
import mk.ukim.finki.emt.carcatalog.domain.models.CarType;
import mk.ukim.finki.emt.carcatalog.domain.models.CarTypeId;
import mk.ukim.finki.emt.carcatalog.service.forms.CarForm;
import mk.ukim.finki.emt.carcatalog.service.forms.CarTypeForm;
import mk.ukim.finki.emt.sharedkernel.domain.valueobjects.CarState;

import java.util.List;
import java.util.Optional;

public interface CarTypeService {
    Optional<CarTypeId> importCarType(CarTypeForm carTypeForm);
    List<CarType> findAll();
    Optional<CarType> findById(CarTypeId carTypeId);
    Optional<CarType> addCar(CarTypeId carTypeId, CarForm carForm) throws CarTypeIdNotExistException;
    void deleteCar(CarTypeId carTypeId, CarId carId) throws CarTypeIdNotExistException, CarIdNotExistException;
    void rentCar(CarTypeId carTypeId, CarId carId) throws CarTypeIdNotExistException,CarIdNotExistException;
    void returnCar(CarTypeId carTypeId, CarId carId, CarState returnState) throws CarTypeIdNotExistException,CarIdNotExistException;
}
