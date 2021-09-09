package mk.ukim.finki.emt.clients.service.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.clients.domain.exceptions.ClientIdNotFoundException;
import mk.ukim.finki.emt.clients.domain.models.Client;
import mk.ukim.finki.emt.clients.domain.models.ClientId;
import mk.ukim.finki.emt.clients.domain.repository.ClientRepository;
import mk.ukim.finki.emt.clients.service.ClientService;
import mk.ukim.finki.emt.clients.service.forms.ClientForm;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.Objects;

@Service
@Transactional
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final Validator validator;

    @Override
    public ClientId addClient(ClientForm clientForm) {
        Objects.requireNonNull(clientForm,  "client form must not be null");
        var constraintViolations = validator.validate(clientForm);
        if (constraintViolations.size() > 0) {
            throw new ConstraintViolationException("The client form is not valid", constraintViolations);
        }
        var newClient = clientRepository.saveAndFlush(toDomainObject(clientForm));
        return newClient.getId();
    }

    private Client toDomainObject(ClientForm clientForm) {
        var client = new Client(clientForm.getClientName(), clientForm.getClientSurname(), clientForm.getClientEmail());
        return client;
    }

    @Override
    public Client findById(ClientId clientId) {
        return this.clientRepository.findById(clientId).orElseThrow(ClientIdNotFoundException::new);
    }
}
