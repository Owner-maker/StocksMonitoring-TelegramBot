package queuemanager.service.consumer;

import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;
import queuemanager.pojo.Consumer;
import queuemanager.service.broker.DataInput;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ConsumerAddressesInputFromXML implements DataInput<Optional<List<Consumer>>,String> {
    public static final String CONSUMERS_XML_FILE_PATH = String.format("%s%s", System.getProperty("user.dir"), "\\src\\main\\resources\\static\\config\\consumers.xml");

    @Override
    public Optional<List<Consumer>> getData(String path) {
        var builderFactory = DocumentBuilderFactory.newInstance();
        var consumersFromXML = new ArrayList<String>();

        try {
            builderFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            var builder = builderFactory.newDocumentBuilder();

            var document = builder.parse(new File(path));
            document.getDocumentElement().normalize();
            var nodeList = document.getElementsByTagName(ConsumersFileXMLTags.GROUP_ITEM_NAME.getTagName());



//            for (int i = 0; i < nodeList.getLength(); i++) {
//                Element broker = (Element) nodeList.item(i);
//                if (broker.hasAttribute(BrokersFileXMLTags.BROKER_ADDRESS_ITEM_NAME.getTagName())) {
//                    String brokerAddress = broker.getAttribute(BrokersFileXMLTags.BROKER_ADDRESS_ITEM_NAME.getTagName());
//                    consumersAddressesFromXML.add(brokerAddress);
//                }
//            }
        }
        catch (ParserConfigurationException | IOException | SAXException e) {
            consumersFromXML = null;
        }
        return Optional.ofNullable(consumersFromXML);
    }
}
