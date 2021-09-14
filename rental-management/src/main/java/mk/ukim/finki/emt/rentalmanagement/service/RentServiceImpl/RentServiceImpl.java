package mk.ukim.finki.emt.rentalmanagement.service.RentServiceImpl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.rentalmanagement.domain.exceptions.RentNotFoundException;
import mk.ukim.finki.emt.rentalmanagement.domain.models.Rent;
import mk.ukim.finki.emt.rentalmanagement.domain.models.RentId;
import mk.ukim.finki.emt.rentalmanagement.domain.repository.RentRepository;
import mk.ukim.finki.emt.rentalmanagement.service.RentService;
import mk.ukim.finki.emt.rentalmanagement.service.forms.RentForm;
import mk.ukim.finki.emt.rentalmanagement.service.forms.ReturnForm;
import mk.ukim.finki.emt.sharedkernel.domain.events.rents.CarRented;
import mk.ukim.finki.emt.sharedkernel.domain.events.rents.CarReturned;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;
import mk.ukim.finki.emt.sharedkernel.domain.valueobjects.Duration;
import mk.ukim.finki.emt.sharedkernel.infra.DomainEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class RentServiceImpl implements RentService {

    private final RentRepository rentRepository;
    private final DomainEventPublisher domainEventPublisher;
    private  final Validator validator;

    @Override
    @Transactional
    public Optional<RentId> rent(RentForm rentForm) throws ParseException {
        Objects.requireNonNull(rentForm, " rent form must not be null");
        var constraintViolations = validator.validate(rentForm);
        if (constraintViolations.size() > 0) {
            throw new ConstraintViolationException("The rent form is not valid", constraintViolations);
        }
        var rent = rentRepository.saveAndFlush(toDomainObject(rentForm));
        this.domainEventPublisher.publish(new CarRented(rent.getCarTypeId().getId(), rent.getCarId().getId()));
        return Optional.of(rent.getId());
    }

    @Override
    @Transactional
    public Optional<Money> returnCar(ReturnForm returnForm) {
        Rent rent = rentRepository.findById(returnForm.getRentId()).orElse(null);
//        rent.changeRenturnDate(new Date());
        domainEventPublisher.publish(new CarReturned(rent.getCarTypeId().getId(), rent.getCarId().getId(), returnForm.getCarState().getState().toString()));
        rentRepository.deleteById(rent.getId());
        return Optional.of(rent.getCarPrice());
    }

    private Rent toDomainObject(RentForm rentForm) throws ParseException {
        var rent = new Rent(new Duration(new SimpleDateFormat("dd/MM/yyyy").parse(rentForm.getStartDate()), new SimpleDateFormat("dd/MM/yyyy").parse(rentForm.getReturnDate())), rentForm.getCarPrice(),rentForm.getCarTypeId(), rentForm.getCarId(), rentForm.getClientId());
        return rent;
    }

    @Override
    public Optional<Rent> findById(RentId rentId) {
        return rentRepository.findById(rentId);
    }

    @Override
    @Transactional
    public Optional<RentId> changeReturnDate(Date returnDate, RentId rentId) throws RentNotFoundException {
       var rent = rentRepository.findById(rentId).orElseThrow(RentNotFoundException::new);
       rent.changeRenturnDate(returnDate);
       var rent1 = rentRepository.saveAndFlush(rent);
       return Optional.of(rent1.getId());
    }

    @Override
    public List<Rent> findAll() {
        return this.rentRepository.findAll();
    }

    @Override
    public void deleteById(RentId rentId) {
        this.rentRepository.deleteById(rentId);
    }
}
