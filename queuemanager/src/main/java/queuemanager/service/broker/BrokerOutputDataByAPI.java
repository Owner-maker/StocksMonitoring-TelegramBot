package queuemanager.service.broker;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import queuemanager.pojo.PartitionInfoCreation;

@Service
public class BrokerOutputDataByAPI implements DataOutput<HttpStatus, String, PartitionInfoCreation> {
    @Override
    public HttpStatus create(String brokerURL, PartitionInfoCreation data) {
        HttpStatus response = null;
        try{
            var restTemplate = new RestTemplate();
            var url = String.format("%s/create", brokerURL);
//            RequestEntity<PartitionInfoCreation> requestEntity =
            ResponseEntity<HttpStatus> responseEntity = restTemplate.exchange(url, HttpMethod.POST,
                    new HttpEntity<>(data), new ParameterizedTypeReference<>() {
                    });

            response = responseEntity.getBody();
        }
        catch (RestClientException e) {
            response = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return response;
    }
}
