package mk.ukim.finki.emt.carcatalog.domain.models;

import lombok.NonNull;
import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;

public class CarTypeId extends DomainObjectId {
    private CarTypeId() {
        super(CarTypeId.randomId(CarTypeId.class).getId());
    }

    public CarTypeId(@NonNull String uuid) {
        super(uuid);
    }

    public static CarTypeId of(String uuid) {
        CarTypeId id = new CarTypeId(uuid);
        return id;
    }
}
