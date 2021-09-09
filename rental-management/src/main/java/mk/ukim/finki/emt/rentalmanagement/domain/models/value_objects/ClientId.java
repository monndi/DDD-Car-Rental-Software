package mk.ukim.finki.emt.rentalmanagement.domain.models.value_objects;

import lombok.NonNull;
import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class ClientId extends DomainObjectId {
    private ClientId() {
        super(ClientId.randomId(ClientId.class).getId());
    }

    public ClientId(@NonNull String uuid) {
        super(uuid);
    }
}
