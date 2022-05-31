package queuemanager.service.broker;

import org.springframework.stereotype.Service;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import queuemanager.pojo.Broker;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BrokerAddressesInputFromXML implements DataInput<Optional<List<Broker>>,String> {
    public static final String BROKERS_XML_FILE_PATH = String.format("%s%s", System.getProperty("user.dir"), "\\src\\main\\resources\\static\\config\\brokers.xml");

    @Override
    public Optional<List<Broker>> getData(String path) {
        var builderFactory = DocumentBuilderFactory.newInstance();
        var brokersFromXML = new ArrayList<Broker>();

        try {
            builderFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            var builder = builderFactory.newDocumentBuilder();

            var document = builder.parse(new File(path));
            document.getDocumentElement().normalize();
            var nodeList = document.getElementsByTagName(BrokersFileXMLTags.BROKER_ITEM_NAME.getTagName());

            for (int i = 0; i < nodeList.getLength(); i++) {
                Element broker = (Element) nodeList.item(i);
                if (broker.hasAttribute(BrokersFileXMLTags.BROKER_HOST.getTagName())
                && broker.hasAttribute(BrokersFileXMLTags.BROKER_PORT.getTagName())) {
                    String host = broker.getAttribute(BrokersFileXMLTags.BROKER_HOST.getTagName());
                    String port = broker.getAttribute(BrokersFileXMLTags.BROKER_PORT.getTagName());
                    brokersFromXML.add(new Broker(host,Integer.parseInt(port)));
                }
            }
        }
        catch (ParserConfigurationException | IOException | SAXException e) {
            brokersFromXML = null;
        }
        return Optional.ofNullable(brokersFromXML);
    }
}
