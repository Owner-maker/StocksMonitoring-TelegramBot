package queuemanager.service.consumer;

import org.springframework.stereotype.Service;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
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

@Service
public class ConsumerAddressesInputFromXML implements DataInput<List<Consumer>, String> {
    public static final String CONSUMERS_XML_FILE_PATH = String.format("%s%s", System.getProperty("user.dir"), "\\src\\main\\resources\\static\\config\\consumers.xml");

    @Override
    public List<Consumer> getData(String path) {
        var builderFactory = DocumentBuilderFactory.newInstance();
        var consumersFromXML = new ArrayList<Consumer>();

        try {
            builderFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            var builder = builderFactory.newDocumentBuilder();

            var document = builder.parse(new File(path));
            document.getDocumentElement().normalize();
            var nodeList = document.getElementsByTagName(ConsumersFileXMLTags.GROUP_ITEM_NAME.getTagName());


            for (int i = 0; i < nodeList.getLength(); i++) {
                var group = nodeList.item(i);
                var groupId = ((Element) group).getAttribute(ConsumersFileXMLTags.GROUP_ID_ITEM.getTagName());

                if (group.getNodeType() == Node.ELEMENT_NODE) {
                    var consumersNodeList = group.getChildNodes();
                    for (int j = 0; j < consumersNodeList.getLength(); j++) {
                        var consumerNode = consumersNodeList.item(j);
                        if (consumerNode.getNodeType() == Node.ELEMENT_NODE) {
                            var consumer = (Element) consumerNode;
                            var host = consumer.getAttribute(ConsumersFileXMLTags.CONSUMER_HOST.getTagName());
                            var port = consumer.getAttribute(ConsumersFileXMLTags.CONSUMER_PORT.getTagName());
                            consumersFromXML.add(new Consumer(host, Integer.parseInt(port), Integer.parseInt(groupId)));
                        }
                    }

                }
            }
        }
        catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
            consumersFromXML = null;
        }
        return consumersFromXML;
    }
}
