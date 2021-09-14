package mk.ukim.finki.emt.clients.domain.models.value_objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import mk.ukim.finki.emt.clients.domain.models.ClientId;
import mk.ukim.finki.emt.sharedkernel.domain.base.ValueObject;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;
import mk.ukim.finki.emt.sharedkernel.domain.valueobjects.Duration;

@Getter
public class Rent  implements ValueObject {
    private final RentId id;
    private final Duration rentDuration;
    private final Money carPrice;

    private final CarId carId;

    private final CarTypeId carTypeId;

    private final ClientId clientId;
    private final Money totalPrice;

    private Rent() {
        this.id = RentId.randomId(RentId.class);
        this.rentDuration = null;
        this.carPrice = null;
        this.carId = null;
        this.carTypeId = null;
        this.clientId = null;
        this.totalPrice = null;
    }

    @JsonCreator
    public Rent(@JsonProperty("id") RentId id,
                   @JsonProperty("rentDuration")Duration rentDuration,
                   @JsonProperty("carPrice") Money carPrice,
                   @JsonProperty("carId")CarId carId,
                   @JsonProperty("carTypeId")CarTypeId carTypeId,
                   @JsonProperty("clientId")ClientId clientId,
                @JsonProperty("totalPrice") Money totalPrice) {
        this.id = id;
        this.rentDuration = rentDuration;
        this.carPrice = carPrice;
        this.carId = carId;
        this.carTypeId = carTypeId;
        this.clientId = clientId;
        this.totalPrice = totalPrice;
    }
}
