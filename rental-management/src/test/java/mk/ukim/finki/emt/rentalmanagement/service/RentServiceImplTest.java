package mk.ukim.finki.emt.rentalmanagement.service;

import mk.ukim.finki.emt.rentalmanagement.domain.models.Rent;
import mk.ukim.finki.emt.rentalmanagement.domain.models.RentId;
import mk.ukim.finki.emt.rentalmanagement.domain.models.value_objects.CarType;
import mk.ukim.finki.emt.rentalmanagement.domain.models.value_objects.ClientId;
import mk.ukim.finki.emt.rentalmanagement.domain.models.value_objects.Duration;
import mk.ukim.finki.emt.rentalmanagement.service.forms.RentForm;
import mk.ukim.finki.emt.rentalmanagement.service.forms.ReturnForm;
import mk.ukim.finki.emt.rentalmanagement.xport.client.RentClient;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Currency;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;
import mk.ukim.finki.emt.sharedkernel.domain.valueobjects.CarState;
import mk.ukim.finki.emt.sharedkernel.domain.valueobjects.State;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class RentServiceImplTest {

    @Autowired
    private RentService rentService;

    @Autowired
    private RentClient rentClient;

    @Test
    public void testRentWithRealData() throws ParseException {
        List<CarType> carTypeList = rentClient.findAll();
        System.out.println(carTypeList);
        CarType c1 = carTypeList.get(0);
        CarType c2 = carTypeList.get(1);

        RentForm rentForm = new RentForm();

        rentForm.setRentDuration(new Duration(new SimpleDateFormat("dd/MM/yyyy").parse("01/09/2021"), new SimpleDateFormat("dd/MM/yyyy").parse("10/09/2021")));
        rentForm.setCarPrice(c1.getCarList().get(0).getPrice());
        rentForm.setCarTypeId(c1.getId());
        rentForm.setCarId(c1.getCarList().get(0).getId());
        rentForm.setClientId(new ClientId("dsdsasda"));

        RentId rentId = rentService.rent(rentForm);

        ReturnForm returnForm = new ReturnForm();
        returnForm.setRentId(rentId);
        returnForm.setCarState(new CarState(State.GOOD));
        Money money = rentService.returnCar(returnForm);
        Assertions.assertEquals(rentService.findById(rentId).getCarPrice(), money);
    }
}
