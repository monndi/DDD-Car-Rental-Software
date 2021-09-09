package mk.ukim.finki.emt.carcatalog.domain.models.value_object;
import lombok.Getter;
import lombok.NonNull;
import mk.ukim.finki.emt.sharedkernel.domain.base.ValueObject;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
@Getter
public class CarStatus implements ValueObject{

    @Enumerated(value = EnumType.STRING)
    private final Status status;

    protected CarStatus() {
        this.status = null;
    }
    public CarStatus(@NonNull Status status) {
        this.status = status;
    }
}
