package mk.ukim.finki.emt.sharedkernel.domain.events.rents;

import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.config.TopicHolder;
import mk.ukim.finki.emt.sharedkernel.domain.events.DomainEvent;

@Getter
public class CarRented extends DomainEvent {
    private String carTypeId;
    private String carId;

    public CarRented() {
        super(TopicHolder.TOPIC_CAR_RENTED);
    }

    public CarRented(String carTypeId, String carId) {
        super(TopicHolder.TOPIC_CAR_RENTED);
        this.carTypeId = carTypeId;
        this.carId = carId;
    }
}
