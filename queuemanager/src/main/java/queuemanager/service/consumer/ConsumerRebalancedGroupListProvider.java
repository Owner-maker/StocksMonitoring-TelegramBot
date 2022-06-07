package queuemanager.service.consumer;

import org.springframework.stereotype.Service;
import queuemanager.pojo.Consumer;
import queuemanager.service.DataProcessor;
import queuemanager.service.broker.BrokerData;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConsumerRebalancedGroupListProvider implements DataProcessor<List<Consumer>,String> {
    private final BrokerData brokerData;
    private final ConsumerData consumerData;

    public ConsumerRebalancedGroupListProvider(BrokerData brokerData, ConsumerData consumerData) {
        this.brokerData = brokerData;
        this.consumerData = consumerData;
    }

    @Override
    public List<Consumer> process(String groupId) {
        List<Consumer> consumerGroup = new ArrayList<>();
        var consumers = consumerData.getConsumerGroups().get(groupId);

        if(){
            
        }



        return null;
    }
}
