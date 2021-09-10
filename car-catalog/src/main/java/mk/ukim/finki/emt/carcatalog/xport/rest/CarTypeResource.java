package mk.ukim.finki.emt.carcatalog.xport.rest;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.carcatalog.domain.models.CarType;
import mk.ukim.finki.emt.carcatalog.service.CarTypeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cartype")
@AllArgsConstructor
public class CarTypeResource {
    private final CarTypeService carTypeService;

    @GetMapping
    public List<CarType> getAll() {
        return carTypeService.findAll();
    }
}
