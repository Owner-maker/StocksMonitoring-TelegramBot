package queuemanager.service.broker;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import queuemanager.pojo.Broker;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BrokerData {
    private final DataInput<Optional<List<String>>, String> addressesInputXML;
    private final DataInput<Optional<List<Broker>>, List<String>> infoInputAPI;
    private List<Broker> brokers;

    public BrokerData(BrokerAddressesInputFromXML addressesInputXML,
                      BrokerInfoInputByAPI infoInputAPI) {
        this.addressesInputXML = addressesInputXML;
        this.infoInputAPI = infoInputAPI;
        this.brokers = new ArrayList<>();
    }

    public List<Broker> getBrokers() {
        if (!this.brokers.isEmpty()) {
            return new ArrayList<>(this.brokers);
        }
        else {
            List<Broker> brokersLoad = null;
            var addresses = addressesInputXML.getData(BrokerAddressesInputFromXML.BROKERS_XML_FILE_PATH)
                    .orElse(new ArrayList<>());
            try {
                brokersLoad = infoInputAPI.getData(addresses).orElse(new ArrayList<>());
            }
            catch (RestClientException e) {
                brokersLoad = new ArrayList<>();
            }
            this.brokers = brokersLoad;
            return brokers;
        }
    }
}
