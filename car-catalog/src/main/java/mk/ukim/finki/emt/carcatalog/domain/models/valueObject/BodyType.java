package mk.ukim.finki.emt.carcatalog.domain.models.valueObject;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class BodyType {
    private final Integer doorsNumber;

    private final boolean hatchback;

    protected BodyType(Integer doorsNumber, boolean hatchback) {
        this.doorsNumber = doorsNumber;
        this.hatchback = hatchback;
    }

}
