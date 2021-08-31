package mk.ukim.finki.emt.rentalmanagement.domain.models.value_objects;

import lombok.Getter;
import lombok.NonNull;
import mk.ukim.finki.emt.sharedkernel.domain.base.ValueObject;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
@Getter
public class CarState implements ValueObject{

    @Enumerated(value = EnumType.STRING)
    private final State state;

    protected CarState() {
        this.state = null;
    }
    public CarState(@NonNull State state) {
        this.state = state;
    }
}
