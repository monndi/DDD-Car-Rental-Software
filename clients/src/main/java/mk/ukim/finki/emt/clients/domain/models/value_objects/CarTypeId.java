package mk.ukim.finki.emt.clients.domain.models.value_objects;

import lombok.NonNull;
import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;

public class CarTypeId extends DomainObjectId {
    private CarTypeId() {
        super(CarId.randomId(CarTypeId.class).getId());
    }

    public CarTypeId(@NonNull String uuid) {
        super(uuid);
    }

    public static CarTypeId of(String uuid) {
        CarTypeId c = new CarTypeId(uuid);
        return c;
    }
}
