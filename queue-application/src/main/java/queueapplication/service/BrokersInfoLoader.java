package queueapplication.service;

import org.springframework.context.annotation.Scope;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import queueapplication.pojo.Broker;
import queueapplication.pojo.Topic;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public final class BrokersInfoLoader {
    private static final String BROKERS_XML_FILE_PATH = String.format("%s%s", System.getProperty("user.dir"), "\\src\\main\\resources\\static\\config\\brokers.xml");
    private static final String BROKER_ITEM_XML_TAG_NAME = "broker";
    private static final String BROKER_ADDRESS_ITEM_XML_TAG_NAME = "address";
    private static final List<String> brokersAddresses;

    static {
        brokersAddresses = getBrokersURLAddressesFromXML();
    }

    public BrokersInfoLoader() {
    }

    private static List<String> getBrokersURLAddressesFromXML() {
        var builderFactory = DocumentBuilderFactory.newInstance();

        try {
            builderFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            var builder = builderFactory.newDocumentBuilder();

            var document = builder.parse(new File(BROKERS_XML_FILE_PATH));
            document.getDocumentElement().normalize();
            var nodeList = document.getElementsByTagName(BROKER_ITEM_XML_TAG_NAME);

            var brokersAddressesFromXML = new ArrayList<String>();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element broker = (Element) nodeList.item(i);
                if (broker.hasAttribute(BROKER_ADDRESS_ITEM_XML_TAG_NAME)) {
                    String brokerAddress = broker.getAttribute(BROKER_ADDRESS_ITEM_XML_TAG_NAME);
                    brokersAddressesFromXML.add(brokerAddress);
                }
            }
            return brokersAddressesFromXML;
        }
        catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();                          // ------------ handle the error or add to the log file ------------
        }
        return new ArrayList<>();
    }

    private static boolean isBrokerAvailable(Broker broker) throws IOException {
        var brokerAddressURL = new URL(broker.getAddressURL());
        HttpURLConnection connection = (HttpURLConnection) brokerAddressURL.openConnection();
        connection.setRequestMethod("GET");
        try {
            int responseCode = connection.getResponseCode();
            return responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_NOT_FOUND;  // ------------ handle the error or add to the log file ------------
        }
        catch (ConnectException e) {
            return false;
        }
    }

    public static List<Broker> getBrokersInfo(){
        List<Broker> brokers = new ArrayList<>();
        try{
            for (String brokerAddress:brokersAddresses){
                var restTemplate = new RestTemplate();
                var url = String.format("%s/getBrokerInfo",brokerAddress);
                ResponseEntity<Map<String,Topic>> responseEntity =restTemplate.exchange(url, HttpMethod.GET,
                        null, new ParameterizedTypeReference<Map<String, Topic>>() {
                        });

                Map<String,Topic> response =responseEntity.getBody();
                brokers.add(new Broker(brokerAddress,response));
            }
            return brokers;
        }
        catch (Exception e){
        }
        return null;
    }
}
