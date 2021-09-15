package mk.ukim.finki.emt.clients.xport;

import mk.ukim.finki.emt.clients.domain.models.ClientId;
import mk.ukim.finki.emt.clients.domain.models.value_objects.Rent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
public class AllRents {
    private final RestTemplate restTemplate;
    private final String serverUrl;

    // Communication point with Bounded Context 3.
    public AllRents(@Value("${app.rent-management.url}") String serverUrl) {
        this.serverUrl = serverUrl;
        this.restTemplate = new RestTemplate();
        var requestFactory = new SimpleClientHttpRequestFactory();
        this.restTemplate.setRequestFactory(requestFactory);
    }

    private UriComponentsBuilder uri() {
        return UriComponentsBuilder.fromUriString(this.serverUrl);
    }
    // Find all rents for specific client.
    public Optional<List<Rent>> findById(ClientId id) {
        try {
            return Optional.of(restTemplate.exchange(uri().path("/api/rents/" + id.getId()).build().toUri(),
                    HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<Rent>>() {

                    }).getBody());
        } catch (Exception e) {
            return Optional.of(Collections.emptyList());
        }
    }
}




