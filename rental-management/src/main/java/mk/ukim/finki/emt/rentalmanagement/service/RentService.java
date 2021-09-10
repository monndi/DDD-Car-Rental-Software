package mk.ukim.finki.emt.rentalmanagement.service;

import mk.ukim.finki.emt.rentalmanagement.domain.exceptions.RentNotFoundException;
import mk.ukim.finki.emt.rentalmanagement.domain.models.Rent;
import mk.ukim.finki.emt.rentalmanagement.domain.models.RentId;
import mk.ukim.finki.emt.rentalmanagement.service.forms.RentForm;
import mk.ukim.finki.emt.rentalmanagement.service.forms.ReturnForm;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;

import java.util.Date;
import java.util.List;

public interface RentService {
    RentId rent(RentForm rentForm);
    Money returnCar(ReturnForm returnForm);
    Rent findById(RentId rentId) throws RentNotFoundException;
    void changeReturnDate(Date returnDate, RentId rentId);
    List<Rent> findAll();
}
