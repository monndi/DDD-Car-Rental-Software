package mk.ukim.finki.emt.rentalmanagement.service.forms;

import lombok.Data;
import mk.ukim.finki.emt.rentalmanagement.domain.models.RentId;
import mk.ukim.finki.emt.rentalmanagement.domain.models.value_objects.CarId;
import mk.ukim.finki.emt.rentalmanagement.domain.models.value_objects.CarTypeId;
import mk.ukim.finki.emt.rentalmanagement.domain.models.value_objects.Duration;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;
import mk.ukim.finki.emt.sharedkernel.domain.valueobjects.CarState;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
public class ReturnForm {
    @Valid
    @NotNull
    private RentId rentId;

    @Valid
    @NotNull
    private CarState carState;
}
