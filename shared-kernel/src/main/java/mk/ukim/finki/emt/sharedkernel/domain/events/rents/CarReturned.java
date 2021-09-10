package mk.ukim.finki.emt.sharedkernel.domain.events.rents;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.config.TopicHolder;
import mk.ukim.finki.emt.sharedkernel.domain.events.DomainEvent;

@Getter
public class CarReturned extends DomainEvent {
    private String carTypeId;
    private String carId;
    private String returnState;
    public CarReturned() {
        super(TopicHolder.TOPIC_CAR_RETURNED);
    }


    public CarReturned(String carTypeId,String carId,String returnState) {
        super(TopicHolder.TOPIC_CAR_RETURNED);
        this.carTypeId = carTypeId;
        this.carId = carId;
        this.returnState = returnState;
    }
}
