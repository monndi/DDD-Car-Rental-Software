package mk.ukim.finki.emt.rentalmanagement.service.RentServiceImpl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.rentalmanagement.domain.exceptions.RentNotFoundException;
import mk.ukim.finki.emt.rentalmanagement.domain.models.Rent;
import mk.ukim.finki.emt.rentalmanagement.domain.models.RentId;
import mk.ukim.finki.emt.rentalmanagement.domain.repository.RentRepository;
import mk.ukim.finki.emt.rentalmanagement.service.RentService;
import mk.ukim.finki.emt.rentalmanagement.service.forms.RentForm;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
@AllArgsConstructor
public class RentServiceImpl implements RentService {

    private final RentRepository rentRepository;
    private  final Validator validator;

    @Override
    public RentId rent(RentForm rentForm) {
        Objects.requireNonNull(rentForm, " rent form must not be null");
        var constraintViolations = validator.validate(rentForm);
        if (constraintViolations.size() > 0) {
            throw new ConstraintViolationException("The rent form is not valid", constraintViolations);
        }
        var rent = rentRepository.saveAndFlush(toDomainObject(rentForm));
        return rent.getId();
    }

    private Rent toDomainObject(RentForm rentForm) {
        var rent= new Rent(rentForm.getRentDuration(), rentForm.getCarPrice(),  rentForm.getCarId(), rentForm.getClientId());
        return rent;
    }

    @Override
    public Rent findById(RentId rentId) throws RentNotFoundException {
        return rentRepository.findById(rentId).orElseThrow(RentNotFoundException::new);
    }

    @Override
    public void changeReturnDate(Date returnDate, RentId rentId) {
       var rent = rentRepository.findById(rentId).orElseThrow(RentNotFoundException::new);
       rent.changeRenturnDate(returnDate);
    }

    @Override
    public List<Rent> findAll() {
        return this.rentRepository.findAll();
    }
}
