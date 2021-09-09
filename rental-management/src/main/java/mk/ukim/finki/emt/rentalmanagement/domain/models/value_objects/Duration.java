package mk.ukim.finki.emt.rentalmanagement.domain.models.value_objects;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import mk.ukim.finki.emt.sharedkernel.domain.base.ValueObject;

import javax.persistence.Embeddable;
import java.text.SimpleDateFormat;
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
    public Duration(@NonNull Date startDate, @NonNull Date returnDate) {
        this.startDate = startDate;
        this.returnDate = returnDate;
    }
    public long getDuration() {
        return  ((returnDate.getTime() - startDate.getTime()) / (1000 * 60 * 60)) % 24;
    }
    public Duration changeReturnDate(Date returnDate) {
        return new Duration(this.startDate, returnDate);
    }
}
