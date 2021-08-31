package mk.ukim.finki.emt.rentalmanagement.domain.models;

import mk.ukim.finki.emt.rentalmanagement.domain.models.value_objects.*;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;

import javax.persistence.*;

@Entity
@Table(name="rents")
public class Rent extends AbstractEntity<RentId> {

    private Duration rentDuration;

    private CarState returnState;

    private Money totalPrice;

    @ManyToOne
    private Car car;

    @AttributeOverride(name="id", column = @Column(name="client_id", nullable = false))
    private ClientId clientId;

}
