package mk.ukim.finki.emt.clients.xport;


import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.clients.domain.models.Client;
import mk.ukim.finki.emt.clients.domain.models.ClientId;
import mk.ukim.finki.emt.clients.domain.models.value_objects.Rent;
import mk.ukim.finki.emt.clients.service.ClientService;
import mk.ukim.finki.emt.clients.service.forms.ClientForm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/client")
@AllArgsConstructor
public class ClientResource {
    private final ClientService clientService;
    private final AllRents allRents;

    @GetMapping
    public List<Client> getAll() {
        return clientService.findAll();
    }
    @PostMapping("/add")
    public ResponseEntity<ClientId> add(@RequestBody ClientForm clientForm) {
        return this.clientService.addClient(clientForm)
                .map(clientId -> ResponseEntity.ok().body(clientId))
                .orElseGet(() -> ResponseEntity.badRequest().build());

    }
    @GetMapping("/rents/{clientId}")
    public ResponseEntity<List<Rent>> getRentsForClient(@PathVariable String clientId) {
        return  this.allRents.findById(ClientId.of(clientId))
                .map(rents -> ResponseEntity.ok().body(rents))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
