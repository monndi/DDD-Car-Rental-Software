package mk.ukim.finki.emt.sharedkernel.domain.dto;

import lombok.Data;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;
import mk.ukim.finki.emt.sharedkernel.domain.valueobjects.CarState;
import mk.ukim.finki.emt.sharedkernel.domain.valueobjects.CarStatus;


@Data
public class CarDto {
    private CarState carState;
    private CarStatus carStatus;
    private Money carPrice;

    private CarDto() { }
    public CarDto(Money carPrice, CarState carState, CarStatus carStatus) {
        this.carState = carState;
        this.carPrice = carPrice;
        this.carStatus = carStatus;
    }
}