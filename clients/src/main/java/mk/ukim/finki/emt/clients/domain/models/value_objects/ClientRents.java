package mk.ukim.finki.emt.clients.domain.models.value_objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import mk.ukim.finki.emt.clients.domain.models.Client;
import mk.ukim.finki.emt.clients.domain.models.ClientId;
import mk.ukim.finki.emt.sharedkernel.domain.base.ValueObject;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;
import mk.ukim.finki.emt.sharedkernel.domain.valueobjects.Duration;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ClientRents implements ValueObject {
    private final Client client;
    private final List<Rent> rents;

    private ClientRents() {
        this.client = null;
        this.rents = new ArrayList<>();
    }

    @JsonCreator
    public ClientRents(@JsonProperty("client") Client client,
                @JsonProperty("rents")List<Rent> rents) {
        this.client = client;
        this.rents = new ArrayList<>();
        this.rents.addAll(rents);
    }
}
