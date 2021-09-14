package mk.ukim.finki.emt.rentalmanagement.service.forms;

import lombok.Data;
import mk.ukim.finki.emt.rentalmanagement.domain.models.RentId;
import mk.ukim.finki.emt.sharedkernel.domain.valueobjects.CarState;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
public class ChangeDateForm {
    @Valid
    @NotNull
    private RentId rentId;

    @Valid
    @NotNull
    private String returnDate;
}
