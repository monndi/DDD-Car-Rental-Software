package mk.ukim.finki.emt.rentalmanagement.domain.models;

import lombok.NonNull;
import mk.ukim.finki.emt.rentalmanagement.domain.models.value_objects.CarTypeId;
import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;

public class RentId extends DomainObjectId {
    private RentId() {
        super(RentId.randomId(RentId.class).getId());
    }

    public RentId(@NonNull String uuid) {
        super(uuid);
    }

    public static RentId of(String uuid) {
        RentId id = new RentId(uuid);
        return id;
    }
}
