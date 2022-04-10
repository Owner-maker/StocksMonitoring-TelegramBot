package queuenode0.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import queuenode0.pojo.Topic;

import java.util.HashMap;
import java.util.Map;

@Service
@Scope("Singleton")
public class Broker {
    private BrokerInfoLoader loader;

    private Map<String, Topic> topics;

    public Broker(BrokerInfoLoader loader) {
        Broker brokerInfo = loader.getBrokerInfo();
        this.topics = brokerInfo.getTopics();
    }

    public Map<String, Topic> getTopics() {
        return new HashMap<>(topics);
    }
}