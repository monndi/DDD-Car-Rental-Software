package mk.ukim.finki.emt.rentalmanagement.service;

import mk.ukim.finki.emt.rentalmanagement.domain.exceptions.RentNotFoundException;
import mk.ukim.finki.emt.rentalmanagement.domain.models.Rent;
import mk.ukim.finki.emt.rentalmanagement.domain.models.RentId;
import mk.ukim.finki.emt.rentalmanagement.service.forms.RentForm;
import mk.ukim.finki.emt.rentalmanagement.service.forms.ReturnForm;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface RentService {
    Optional<RentId> rent(RentForm rentForm) throws ParseException;
    Optional<Money> returnCar(ReturnForm returnForm);
    Optional<Rent> findById(RentId rentId);
    Optional<RentId> changeReturnDate(Date returnDate, RentId rentId) throws RentNotFoundException;
    List<Rent> findAll();
    void deleteById(RentId rentId);
}
