package mk.ukim.finki.emt.rentalmanagement.xport.rest;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.rentalmanagement.domain.models.Rent;
import mk.ukim.finki.emt.rentalmanagement.domain.models.RentId;
import mk.ukim.finki.emt.rentalmanagement.domain.models.value_objects.ClientId;
import mk.ukim.finki.emt.rentalmanagement.service.RentService;
import mk.ukim.finki.emt.rentalmanagement.service.forms.ChangeDateForm;
import mk.ukim.finki.emt.rentalmanagement.service.forms.RentForm;
import mk.ukim.finki.emt.rentalmanagement.service.forms.ReturnForm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/rents")
@AllArgsConstructor
public class RentsResource {
    private final RentService rentService;

    @GetMapping("/{clientId}")
    public List<Rent> getRentsById(@PathVariable ClientId clientId) {
        return rentService.findAll().stream().filter(x->x.getClientId().getId().compareTo(clientId.getId()) == 0).collect(Collectors.toList());
    }
    @GetMapping
    public List<Rent> getAll() {
        return rentService.findAll();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable String id) {
        this.rentService.deleteById(RentId.of(id));
        if(this.rentService.findById(RentId.of(id)).isEmpty()) return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }
    @PostMapping("/rent")
    public ResponseEntity rentCar(@RequestBody RentForm rentForm) throws ParseException {
        return this.rentService.rent(rentForm)
                .map(rentId -> ResponseEntity.ok().body(rentId))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
    @PostMapping("/returnCar")
    public ResponseEntity returnCar(@RequestBody ReturnForm returnForm) {
        return this.rentService.returnCar(returnForm)
                .map(money -> ResponseEntity.ok().body(money))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping("/changeDate")
    public ResponseEntity<RentId> changeReturnDate(@RequestBody ChangeDateForm changeDateForm) throws ParseException {
        return this.rentService.changeReturnDate(new SimpleDateFormat("dd/MM/yyyy").parse(changeDateForm.getReturnDate()), changeDateForm.getRentId())
                .map(rentId -> ResponseEntity.ok().body(rentId))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
