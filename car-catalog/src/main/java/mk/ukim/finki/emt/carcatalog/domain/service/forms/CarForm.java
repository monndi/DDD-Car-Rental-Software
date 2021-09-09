package mk.ukim.finki.emt.carcatalog.domain.service.forms;

import lombok.Data;
import mk.ukim.finki.emt.sharedkernel.domain.valueobjects.CarState;
import mk.ukim.finki.emt.carcatalog.domain.models.value_object.CarStatus;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;

import javax.validation.constraints.NotNull;

@Data
public class CarForm {
    @NotNull
    private Money price;

    @NotNull
    private CarStatus carStatus;

    @NotNull
    private CarState carState;
}
