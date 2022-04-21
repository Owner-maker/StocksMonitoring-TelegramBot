package queueapplication.service.broker;

import org.springframework.stereotype.Service;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BrokerAddressesInputFromXML implements DataInput<Optional<List<String>>,String> {
    public static final String BROKERS_XML_FILE_PATH = String.format("%s%s", System.getProperty("user.dir"), "\\src\\main\\resources\\static\\config\\brokers.xml");
    public

    @Override
    public Optional<List<String>> getData(String path) {
        var builderFactory = DocumentBuilderFactory.newInstance();
        var brokersAddressesFromXML = new ArrayList<String>();

        try {
            builderFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            var builder = builderFactory.newDocumentBuilder();

            var document = builder.parse(new File(path));
            document.getDocumentElement().normalize();
            var nodeList = document.getElementsByTagName(BrokersFileXMLTags.BROKER_ITEM_NAME.getTagName());

            for (int i = 0; i < nodeList.getLength(); i++) {
                Element broker = (Element) nodeList.item(i);
                if (broker.hasAttribute(BrokersFileXMLTags.BROKER_ADDRESS_ITEM_NAME.getTagName())) {
                    String brokerAddress = broker.getAttribute(BrokersFileXMLTags.BROKER_ADDRESS_ITEM_NAME.getTagName());
                    brokersAddressesFromXML.add(brokerAddress);
                }
            }
        }
        catch (ParserConfigurationException | IOException | SAXException e) {
            brokersAddressesFromXML = null;
        }
        return Optional.ofNullable(brokersAddressesFromXML);
    }
}
