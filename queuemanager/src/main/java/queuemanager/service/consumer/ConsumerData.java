package queuemanager.service.consumer;

import org.springframework.stereotype.Service;
import queuemanager.pojo.Consumer;
import queuemanager.service.URLAddressAvailability;
import queuemanager.service.broker.DataInput;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConsumerData {
    private final DataInput<List<Consumer>, String> addressesInputXML;
    private final URLAddressAvailability urlAddressAvailability;
    private List<Consumer> consumers;

    public ConsumerData(ConsumerAddressesInputFromXML addressesInputXML,
                        URLAddressAvailability urlAddressAvailability) {
        this.addressesInputXML = addressesInputXML;
        this.urlAddressAvailability = urlAddressAvailability;
        this.consumers = new ArrayList<>();
    }

    public List<Consumer> getConsumers() {
        if (!this.consumers.isEmpty()) {
            return new ArrayList<>(this.consumers);
        }
        else {
            List<Consumer> consumersLoad = new ArrayList<>();
            var consumersFromXML = addressesInputXML.getData(ConsumerAddressesInputFromXML.CONSUMERS_XML_FILE_PATH);
            for (Consumer consumer : consumersFromXML) {
                if (urlAddressAvailability.isAddressAvailable(
                        consumer.getHost(),
                        consumer.getPort(),
                        URLAddressAvailability.DEFAULT_TIMEOUT)) {
                    consumersLoad.add(consumer);
                }
            }
            this.consumers = consumersLoad;
            return consumersLoad;
        }
    }
}
