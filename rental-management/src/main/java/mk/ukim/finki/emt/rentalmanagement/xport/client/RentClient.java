package mk.ukim.finki.emt.rentalmanagement.xport.client;

import mk.ukim.finki.emt.rentalmanagement.domain.models.value_objects.CarType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;

@Service
public class RentClient {

    private final RestTemplate restTemplate;
    private final String serverUrl;

    // Communication point with Bounded Context 1
    public RentClient(@Value("${app.car-catalog.url}") String serverUrl) {
        this.serverUrl = serverUrl;
        this.restTemplate = new RestTemplate();
        var requestFactory = new SimpleClientHttpRequestFactory();
        this.restTemplate.setRequestFactory(requestFactory);
    }

    private UriComponentsBuilder uri() {
        return UriComponentsBuilder.fromUriString(this.serverUrl);
    }
    // Get All available CarTypes and Cars
    public List<CarType> findAll() {
        try {
            return restTemplate.exchange(uri().path("/api/cartype").build().toUri(),
                    HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<CarType>>() {

            }).getBody();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }
}
