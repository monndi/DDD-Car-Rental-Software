package mk.ukim.finki.emt.clients.domain.models;

import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;

import javax.persistence.*;

@Entity
@Table(name="clients")
public class Client extends AbstractEntity<ClientId> {
    private String clientName;
    private String clientSurname;
    private String clientEmail;
}
