package mk.ukim.finki.emt.clients.xport.rest;


import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.clients.domain.models.Client;
import mk.ukim.finki.emt.clients.domain.models.ClientId;
import mk.ukim.finki.emt.clients.domain.models.value_objects.ClientRents;
import mk.ukim.finki.emt.clients.domain.models.value_objects.Rent;
import mk.ukim.finki.emt.clients.service.ClientService;
import mk.ukim.finki.emt.clients.service.forms.ClientForm;
import mk.ukim.finki.emt.clients.xport.AllRents;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/client")
@AllArgsConstructor
public class ClientResource {
    private final ClientService clientService;
    private final AllRents allRents;

    @GetMapping
    public List<ClientRents> getAll() {
        List<ClientRents> clientRents = new ArrayList<>();
        for (Client client: clientService.findAll()) {
            List<Rent> rents = this.allRents.findById(client.getId()).get();
            clientRents.add(new ClientRents(client, rents));
        }
        return clientRents;
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
    @DeleteMapping("delete/{id}")
    public void deleteClientById(@PathVariable String id) {
        this.clientService.deleteClientById(ClientId.of(id));
    }
}
