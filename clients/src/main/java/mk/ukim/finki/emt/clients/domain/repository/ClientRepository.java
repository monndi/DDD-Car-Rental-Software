package mk.ukim.finki.emt.clients.domain.repository;

import mk.ukim.finki.emt.clients.domain.models.Client;
import mk.ukim.finki.emt.clients.domain.models.ClientId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, ClientId> {
}
