package queuemanager.service.consumer;

import org.springframework.stereotype.Service;
import queuemanager.pojo.Consumer;
import queuemanager.pojo.ConsumerGroup;
import queuemanager.service.URLAddressAvailability;
import queuemanager.service.broker.DataInput;

import java.util.HashMap;
import java.util.Map;

@Service
public class ConsumerData {
    private final DataInput<Map<String, ConsumerGroup>, String> addressesInputXML;
    private final URLAddressAvailability urlAddressAvailability;
    private Map<String, ConsumerGroup> consumerGroups;

    public ConsumerData(ConsumersInputFromXML addressesInputXML,
                        URLAddressAvailability urlAddressAvailability) {
        this.addressesInputXML = addressesInputXML;
        this.urlAddressAvailability = urlAddressAvailability;
        this.consumerGroups = new HashMap<>();
    }

    public Map<String, ConsumerGroup> getConsumerGroups() {
        if (!this.consumerGroups.isEmpty()) {
            return new HashMap<>(this.consumerGroups);
        }
        else {
            var consumersGroupsLoad = new HashMap<String, ConsumerGroup>();
            var consumersFromXML = addressesInputXML.getData(ConsumersInputFromXML.CONSUMERS_XML_FILE_PATH);

            consumersFromXML.values()
                    .forEach(group -> {
                        var isGroupLeaderAvailableCreation = true;
                        var consumerGroupLoad = new ConsumerGroup(group.getGroupId(), group.getTopicName());
                        for (Consumer consumer : group.getConsumers()) {
                            if (urlAddressAvailability.isAddressAvailable(
                                    consumer.getHost(),
                                    consumer.getPort(),
                                    URLAddressAvailability.DEFAULT_TIMEOUT)) {
                                if (isGroupLeaderAvailableCreation) {
                                    group.setGroupLeader(consumer);
                                    isGroupLeaderAvailableCreation = false;
                                }
                                consumerGroupLoad.addConsumer(consumer);
                            }
                        }
                        consumersGroupsLoad.put(consumerGroupLoad.getGroupId(), consumerGroupLoad);
                    });

            this.consumerGroups = consumersGroupsLoad;
            return consumersGroupsLoad;
        }
    }
}
