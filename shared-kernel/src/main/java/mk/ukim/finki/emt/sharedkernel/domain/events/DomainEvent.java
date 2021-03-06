package mk.ukim.finki.emt.sharedkernel.domain.events;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.Getter;

import java.time.Instant;

@Getter
public abstract class DomainEvent {
    private String topic;
    private Instant occuredOn;

    public DomainEvent(String topic) {
        this.occuredOn = Instant.now();
        this.topic = topic;
    }

    public String toJson() {
//        ObjectMapper objectMapper = new ObjectMapper();
        Gson gson = new Gson();
        String output = null;
        try {
           output = gson.toJson(this);
//            output = objectMapper.writeValueAsString(this);
        } catch (Exception e) {

        }
        return output;
    }

    public String topic() {
        return topic;
    }

    public static <E extends DomainEvent> E fromJson(String json, Class<E> eventClass) throws JsonProcessingException {
//        ObjectMapper objectMapper = new ObjectMapper();
        Gson gson = new Gson();
        return gson.fromJson(json, eventClass);
    }

}
