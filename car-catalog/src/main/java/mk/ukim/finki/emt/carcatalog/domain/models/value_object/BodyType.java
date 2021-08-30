package mk.ukim.finki.emt.carcatalog.domain.models.value_object;

import lombok.Getter;
import lombok.NonNull;
import mk.ukim.finki.emt.sharedkernel.domain.base.ValueObject;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class BodyType implements ValueObject {
    private final int doorsNumber;

    private final boolean hatchback;

    protected BodyType() {
        this.doorsNumber = 0;
        this.hatchback = false;
    }
    public BodyType(@NonNull Integer doorsNumber,@NonNull boolean hatchback) {
        this.doorsNumber = doorsNumber;
        this.hatchback = hatchback;
    }
}
