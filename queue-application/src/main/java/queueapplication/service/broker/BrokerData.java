package queueapplication.service.broker;

import org.springframework.stereotype.Service;
import queueapplication.pojo.Broker;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BrokerData {
    private final DataInput<Optional<List<String>>,String> addressesInputXML;
    private final DataInput<Optional<List<Broker>>, List<String>> infoInputAPI;

    public BrokerData(BrokerAddressesInputFromXML addressesInputXML,
                      BrokerInfoInputByAPI infoInputAPI) {
        this.addressesInputXML = addressesInputXML;
        this.infoInputAPI = infoInputAPI;
    }

    public List<Broker> getBrokers(){
        var addresses = addressesInputXML.getData(BrokerAddressesInputFromXML.BROKERS_XML_FILE_PATH)
                .orElse(new ArrayList<>());
        return infoInputAPI.getData(addresses).orElse(new ArrayList<>());
    }
}
