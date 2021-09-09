package mk.ukim.finki.emt.carcatalog.domain.models.value_object;

import lombok.NonNull;
import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class CarTypeId extends DomainObjectId {
    private CarTypeId() {
        super(CarTypeId.randomId(CarTypeId.class).getId());
    }

    public CarTypeId(@NonNull String uuid) {
        super(uuid);
    }
}
