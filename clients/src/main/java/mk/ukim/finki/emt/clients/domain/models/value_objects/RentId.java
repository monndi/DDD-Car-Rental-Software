package mk.ukim.finki.emt.clients.domain.models.value_objects;

import lombok.NonNull;
import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;

public class RentId extends DomainObjectId{
    private RentId() {
        super(RentId.randomId(RentId.class).getId());
    }

    public RentId(@NonNull String uuid) {
        super(uuid);
    }

    public static RentId of(String uuid) {
        RentId c = new RentId(uuid);
        return c;
    }
}
