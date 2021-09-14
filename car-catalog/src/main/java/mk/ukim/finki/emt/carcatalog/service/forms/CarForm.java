package mk.ukim.finki.emt.carcatalog.service.forms;

import lombok.Data;
import mk.ukim.finki.emt.sharedkernel.domain.valueobjects.CarState;
import mk.ukim.finki.emt.sharedkernel.domain.valueobjects.CarStatus;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;

import javax.validation.constraints.NotNull;

@Data
public class CarForm {
    @NotNull
    private String carStatus;

    @NotNull
    private String carState;
}
