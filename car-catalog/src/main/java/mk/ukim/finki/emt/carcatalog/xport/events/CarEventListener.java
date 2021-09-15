package mk.ukim.finki.emt.carcatalog.xport.events;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.carcatalog.domain.models.CarId;
import mk.ukim.finki.emt.carcatalog.domain.models.CarTypeId;
import mk.ukim.finki.emt.carcatalog.service.CarTypeService;
import mk.ukim.finki.emt.sharedkernel.domain.config.TopicHolder;
import mk.ukim.finki.emt.sharedkernel.domain.events.DomainEvent;
import mk.ukim.finki.emt.sharedkernel.domain.events.rents.CarRented;
import mk.ukim.finki.emt.sharedkernel.domain.events.rents.CarReturned;
import mk.ukim.finki.emt.sharedkernel.domain.valueobjects.CarState;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CarEventListener {

    private final CarTypeService carTypeService;

    // Rent car event from Bounded Context 2, listen when a car is rented and make the changes.
    @KafkaListener(topics=TopicHolder.TOPIC_CAR_RENTED, groupId = "carCatalog")
    public void consumerCarRentedEvent(String jsonMessage) {
        try {
            CarRented event = DomainEvent.fromJson(jsonMessage, CarRented.class);
            carTypeService.rentCar(CarTypeId.of(event.getCarTypeId()), CarId.of(event.getCarId()));
        } catch (Exception e) {}
    }

    // Return car event from Bounded Context 1, listen when a car is returned and make the changes.
    @KafkaListener(topics=TopicHolder.TOPIC_CAR_RETURNED, groupId = "carCatalog")
    public void consumerCarReturnedEvent(String jsonMessage) {
        try {
            CarReturned event = DomainEvent.fromJson(jsonMessage, CarReturned.class);
            carTypeService.returnCar(CarTypeId.of(event.getCarTypeId()), CarId.of(event.getCarId()), CarState.of(event.getReturnState()));
        } catch (Exception e) {}
    }
}
