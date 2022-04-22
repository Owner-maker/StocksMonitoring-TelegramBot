package queue_manager.service.broker;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import queue_manager.pojo.BrokerInfoCreation;

@Service
public class BrokerOutputDataByAPI implements DataOutput<HttpStatus, String, BrokerInfoCreation> {
    @Override
    public HttpStatus create(String brokerURL, BrokerInfoCreation data) {
        HttpStatus response = null;
        try{
            var restTemplate = new RestTemplate();
            var url = String.format(
                    "%s/create?topicName=%s&partitionQuantity=%s", brokerURL,
                    data.getTopicName(), data.getPartitionQuantity());
            ResponseEntity<HttpStatus> responseEntity = restTemplate.exchange(url, HttpMethod.POST,
                    null, new ParameterizedTypeReference<>() {
                    });

            response = responseEntity.getBody();
        }
        catch (RestClientException e) {
            response = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return response;
    }
}
