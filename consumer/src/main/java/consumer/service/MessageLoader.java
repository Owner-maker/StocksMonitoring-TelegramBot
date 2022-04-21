package consumer.service;

import consumer.pojo.Offset;
import org.springframework.context.annotation.Scope;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

@Service
@Scope("singleton")
public class MessageLoader {

    private OffsetsLoader loader;

    public MessageLoader(OffsetsLoader loader) {
        this.loader = loader;
    }

    public String getMessage(String topicName,String key, int offset){
        try{
            var restTemplate = new RestTemplate();
            var url = String.format("%s/readMessage?topicName=%s&key=%s&offset=%s", OffsetsLoader.BROKER_URL_ADDRESS,topicName,key,String.valueOf(offset));
            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET,
                    null, new ParameterizedTypeReference<>() {
                    });

            String message =  responseEntity.getBody();
            if(message!=null){
                url = String.format("%s/incrementOffset?topicName=%s&groupId=TestGroupId",OffsetsLoader.BROKER_URL_ADDRESS,topicName);
                restTemplate.exchange(url, HttpMethod.POST,
                        null, new ParameterizedTypeReference<>() {
                        });
            }
            return message;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
