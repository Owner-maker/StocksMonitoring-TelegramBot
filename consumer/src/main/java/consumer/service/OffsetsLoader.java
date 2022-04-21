package consumer.service;

import consumer.pojo.Offset;
import org.springframework.context.annotation.Scope;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Scope("singleton")
public class OffsetsLoader {

    public static final String  BROKER_URL_ADDRESS = "http://localhost:9090";

    private final Map<String,List<Offset>> users;

    public OffsetsLoader(){
        users = new HashMap<>();
        users.put("TestGroupId",getGroupIdOffsetsFromBroker("TestGroupId"));
    }

    public Map<String, List<Offset>> getUsers() {
        return users;
    }

    public static List<Offset> getGroupIdOffsetsFromBroker(String groupId){
        try{
            var restTemplate = new RestTemplate();
            var url = String.format("%s/getOffsets?groupId=%s", BROKER_URL_ADDRESS,groupId);
            ResponseEntity<List<Offset>> responseEntity = restTemplate.exchange(url, HttpMethod.GET,
                    null, new ParameterizedTypeReference<>() {
                    });

            return responseEntity.getBody();
        }
        catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
