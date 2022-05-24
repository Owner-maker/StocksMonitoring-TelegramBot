package queuemanager.service.consumer;

import org.springframework.stereotype.Service;
import queuemanager.pojo.Consumer;
import queuemanager.service.broker.DataInput;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ConsumerData {
    private final DataInput<Optional<List<Consumer>>, String> addressesInputXML;
    private static List<Consumer> consumers;

    public ConsumerData(ConsumerAddressesInputFromXML addressesInputXML) {
        this.addressesInputXML = addressesInputXML;
        this.consumers = new ArrayList<>();
    }

    public List<Consumer> getConsumers() {
        if (!consumers.isEmpty()) {
            return consumers;
        }
        consumers = addressesInputXML.getData(ConsumerAddressesInputFromXML.CONSUMERS_XML_FILE_PATH)
                .orElse(new ArrayList<>());
        return consumers;
    }
}
