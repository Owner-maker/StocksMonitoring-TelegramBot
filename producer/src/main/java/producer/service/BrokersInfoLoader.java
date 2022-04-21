package producer.service;

import org.springframework.context.annotation.Scope;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import producer.pojo.Broker;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Scope("singleton")
public final class BrokersInfoLoader {
    public static final String CLUSTER_URL_ADDRESS = "http://localhost:8090";

    private final List<Broker> brokers;

    public BrokersInfoLoader() {
        brokers = loadBrokersInfo();
    }

    public List<Broker> getBrokers() {
        return new ArrayList<>(brokers);
    }

    public static List<Broker> loadBrokersInfo() {
        try{
            var restTemplate = new RestTemplate();
            var url = String.format("%s/queue/getBrokers", CLUSTER_URL_ADDRESS);
            ResponseEntity<List<Broker>> responseEntity = restTemplate.exchange(url, HttpMethod.GET,
                    null, new ParameterizedTypeReference<List<Broker>>() {
                    });

            return responseEntity.getBody();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
