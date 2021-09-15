package mk.ukim.finki.emt.sharedkernel.domain.valueobjects;

import lombok.Getter;
import lombok.NonNull;
import mk.ukim.finki.emt.sharedkernel.domain.base.ValueObject;

import javax.persistence.Embeddable;
import java.util.Date;

@Embeddable
@Getter
public class Duration implements ValueObject {
    private final Date startDate;
    private final Date returnDate;

    protected Duration() {
        this.startDate = null;
        this.returnDate = null;
    }
    // Value object for Bounded Context 2
    public Duration(@NonNull Date startDate, @NonNull Date returnDate) {
        this.startDate = startDate;
        this.returnDate = returnDate;
    }
    // Calculate the duration in hours for a specific rent
    public long getDuration() {
        return  (((returnDate.getTime() - startDate.getTime()) / 1000) / 3600);
    }
    public Duration changeReturnDate(Date returnDate) {
        return new Duration(this.startDate, returnDate);
    }
}
