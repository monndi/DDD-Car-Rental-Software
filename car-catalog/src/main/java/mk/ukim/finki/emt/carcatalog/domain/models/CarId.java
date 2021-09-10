package mk.ukim.finki.emt.carcatalog.domain.models;

import lombok.NonNull;
import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;

public class CarId extends DomainObjectId {
    private CarId() {
        super(CarId.randomId(CarId.class).getId());
    }

    public CarId(@NonNull String uuid) {
        super(uuid);
    }

    public static CarId of(String uuid) {
        CarId id = new CarId(uuid);
        return id;
    }

}
