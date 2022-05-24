package queuemanager.service.broker;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import queuemanager.pojo.Broker;
import queuemanager.service.URLAddressAvailability;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BrokerInfoInputByAPI implements DataInput<Optional<List<Broker>>, List<String>> {

    private URLAddressAvailability urlAddressAvailability;

    public BrokerInfoInputByAPI(URLAddressAvailability urlAddressAvailability) {
        this.urlAddressAvailability = urlAddressAvailability;
    }

    @Override
    public Optional<List<Broker>> getData(List<String> brokersAddresses) throws RestClientException {
        List<Broker> brokers = new ArrayList<>();
        for (String brokerAddress : brokersAddresses) {
            var connection = urlAddressAvailability.isAddressAvailable(
                    brokerAddress, URLAddressAvailability.DEFAULT_TIMEOUT);
            if (connection) {
                var restTemplate = new RestTemplate();
                var url = String.format("%s/getBrokerInfo", brokerAddress);
                ResponseEntity<Broker> responseEntity = restTemplate.exchange(url, HttpMethod.GET,
                        null, new ParameterizedTypeReference<>() {
                        });

                Broker response = responseEntity.getBody();
                brokers.add(response);
            }

        }
        return Optional.of(brokers);
    }
}
