package mk.ukim.finki.emt.rentalmanagement.service.forms;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.Data;
import mk.ukim.finki.emt.rentalmanagement.domain.models.value_objects.*;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;

@Data
public class RentForm {

    @Valid
    @NotNull
    private CarTypeId carTypeId;

    @Valid
    @NotNull
    private CarId carId;

    @Valid
    @NotNull
    private Duration rentDuration;

    @Valid
    @NotNull
    private Money carPrice;

    @Valid
    @NotNull
    private ClientId clientId;
}
