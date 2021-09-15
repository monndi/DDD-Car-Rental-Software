package mk.ukim.finki.emt.clients.service;

import mk.ukim.finki.emt.clients.domain.models.Client;
import mk.ukim.finki.emt.clients.domain.models.ClientId;
import mk.ukim.finki.emt.clients.service.forms.ClientForm;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    Optional<ClientId> addClient(ClientForm clientForm);

    Client findById(ClientId clientId);

    public List<Client> findAll();

    void deleteClientById(ClientId clientId);
}
