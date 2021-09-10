package mk.ukim.finki.emt.rentalmanagement.xport.rest;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.rentalmanagement.domain.models.Rent;
import mk.ukim.finki.emt.rentalmanagement.domain.models.value_objects.ClientId;
import mk.ukim.finki.emt.rentalmanagement.service.RentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/rents")
@AllArgsConstructor
public class RentsResource {
    private final RentService rentService;

    @GetMapping("/{id}")
    public List<Rent> getRentsById(@PathVariable ClientId clientId) {
        return rentService.findAll().stream().filter(x->x.getClientId().getId().compareTo(clientId.getId()) == 0).collect(Collectors.toList());
    }
}
