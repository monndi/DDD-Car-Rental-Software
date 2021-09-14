package mk.ukim.finki.emt.carcatalog.xport.rest;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.carcatalog.domain.exceptions.CarTypeIdNotExistException;
import mk.ukim.finki.emt.carcatalog.domain.models.CarType;
import mk.ukim.finki.emt.carcatalog.domain.models.CarTypeId;
import mk.ukim.finki.emt.carcatalog.service.CarTypeService;
import mk.ukim.finki.emt.carcatalog.service.forms.CarForm;
import mk.ukim.finki.emt.carcatalog.service.forms.CarTypeForm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/cartype")
@AllArgsConstructor
public class CarTypeResource {
    private final CarTypeService carTypeService;

    @GetMapping
    public List<CarType> getAll() {
        return carTypeService.findAll();
    }

    @GetMapping("/getCar/{carTypeId}")
    public CarType getCarTypeById(@PathVariable String carTypeId) {
        return this.carTypeService.findById(CarTypeId.of(carTypeId)).orElseThrow(CarTypeIdNotExistException::new);
    }

    @PostMapping("/add")
    public ResponseEntity<CarTypeId> add(@RequestBody CarTypeForm carTypeForm) {
        return this.carTypeService.importCarType(carTypeForm)
                .map(carTypeId -> ResponseEntity.ok().body(carTypeId))
                .orElseGet(() -> ResponseEntity.badRequest().build());

    }
    @PutMapping("/addCar/{id}")
    public ResponseEntity<CarType> addCar(@PathVariable String id, @RequestBody CarForm carForm) {
        return this.carTypeService.addCar(CarTypeId.of(id), carForm)
                .map(carType -> ResponseEntity.ok().body(carType))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

}
