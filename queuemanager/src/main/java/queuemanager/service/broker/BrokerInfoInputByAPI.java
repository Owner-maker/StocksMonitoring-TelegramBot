package queuemanager.service.broker;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import queuemanager.pojo.Broker;
import queuemanager.service.URLAddressAvailability;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BrokerInfoInputByAPI implements DataInput<Optional<List<Broker>>, List<Broker>> {
    private static final String HTTP_GET_BROKERS = "http://%s:%s/getBrokerInfo";
    private final URLAddressAvailability urlAddressAvailability;

    public BrokerInfoInputByAPI(URLAddressAvailability urlAddressAvailability) {
        this.urlAddressAvailability = urlAddressAvailability;
    }

    @Override
    public Optional<List<Broker>> getData(List<Broker> brokers) throws RestClientException {
        List<Broker> brokersLoad = new ArrayList<>();
        for (Broker broker : brokers) {
            if (urlAddressAvailability.isAddressAvailable(
                    broker.getHost(),
                    broker.getPort(),
                    URLAddressAvailability.DEFAULT_TIMEOUT)) {
                var restTemplate = new RestTemplate();
                var url = String.format(HTTP_GET_BROKERS, broker.getHost(), broker.getPort());
                ResponseEntity<Broker> responseEntity = restTemplate.exchange(url, HttpMethod.GET,
                        null, new ParameterizedTypeReference<>() {
                        });

                Broker response = responseEntity.getBody();
                brokersLoad.add(response);
            }

        }
        return Optional.of(brokersLoad);
    }
}
