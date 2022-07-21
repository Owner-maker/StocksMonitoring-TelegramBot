package queuemanager.service.consumer;

import org.springframework.stereotype.Service;
import queuemanager.pojo.*;
import queuemanager.service.DataProcessor;
import queuemanager.service.broker.BrokerData;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ConsumerRebalancedGroupListProvider implements DataProcessor<List<Consumer>, String> {
    private final BrokerData brokerData;
    private final ConsumerData consumerData;

    public ConsumerRebalancedGroupListProvider(BrokerData brokerData, ConsumerData consumerData) {
        this.brokerData = brokerData;
        this.consumerData = consumerData;
    }

    @Override
    public List<Consumer> process(String groupId) {
        var rebalancedConsumerGroup = new ArrayList<Consumer>();
        var consumerGroup = Optional.of(consumerData.getConsumerGroups().get(groupId));
        var topicName = consumerGroup.orElse(new ConsumerGroup()).getTopicName();
        if (consumerGroup.orElse(new ConsumerGroup()).getConsumers().isEmpty() ||
                topicName.equals(ConsumersInputFromXML.BASIC_TOPIC_VALUE)) {
            return rebalancedConsumerGroup;
        }

        var brokers = brokerData.getBrokers();
        int partitionsAmount = 0;
        for (Broker broker : brokers) {
            var topic = Optional.of(broker.getTopics().get(topicName));
            partitionsAmount += topic.orElse(new Topic()).getPartitions().size();
        }
        int amountOfPartitionsPerConsumer = partitionsAmount / consumerGroup.get().getConsumers().size();
        int remainingAmountOfPartitions = partitionsAmount % consumerGroup.get().getConsumers().size();
        var consumers = consumerGroup.get().getConsumers();
        var consumersAmount = consumerGroup.get().getConsumers().size();


        for (Broker broker : brokers) {
            var topics = broker.getTopics();
            if (topics.containsKey(topicName)) {
                var partitions = topics.get(topicName).getPartitions();
                for (Partition partition : partitions) {
                    if (consumersAmount > 0) {
                        int currentAmountOfPartitionPerConsumer = amountOfPartitionsPerConsumer;
                        if (remainingAmountOfPartitions > 0){
                            currentAmountOfPartitionPerConsumer += 1;
                            remainingAmountOfPartitions--;
                        }

                    }
                }
            }
        }


        return rebalancedConsumerGroup;
    }
}
