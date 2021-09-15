package mk.ukim.finki.emt.clients.service.forms;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.Data;

// ClientDto for adding new Client.
@Data
public class ClientForm {
    @Valid
    @NotNull
    private String clientId;
    @Valid
    @NotNull
    private String clientName;
    @Valid
    @NotNull
    private String clientSurname;
    @Valid
    @NotNull
    private String clientEmail;
}
