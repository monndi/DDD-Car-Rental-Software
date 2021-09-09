package mk.ukim.finki.emt.rentalmanagement.domain.models;

import lombok.NonNull;
import mk.ukim.finki.emt.rentalmanagement.domain.models.value_objects.*;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Currency;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name="rents")
public class Rent extends AbstractEntity<RentId> {

    private Duration rentDuration;

    @AttributeOverrides({@AttributeOverride(name = "amount", column =
    @Column(name = "price")), @AttributeOverride(name = "currency", column =
    @Column(name = "price_currency"))})
    private Money carPrice;

    @AttributeOverride(name="id", column = @Column(name="car_id", nullable = false))
    private CarId carId;

    @AttributeOverride(name="id", column = @Column(name="client_id", nullable = false))
    private ClientId clientId;

    private Rent() {
        super(RentId.randomId(RentId.class));
    }


    public Rent(Duration rentDuration, Money carPrice, CarId carId, ClientId clientId) {
        super(RentId.randomId(RentId.class));
        this.rentDuration = rentDuration;
        this.carPrice = carPrice;
        this.carId = carId;
        this.clientId = clientId;
    }

    public Money totalPrice() {
        return carPrice.multiply(rentDuration.getDuration());
    }

    public void changeRenturnDate(Date returnDate) {
        this.rentDuration = this.rentDuration.changeReturnDate(returnDate);
    }

}
