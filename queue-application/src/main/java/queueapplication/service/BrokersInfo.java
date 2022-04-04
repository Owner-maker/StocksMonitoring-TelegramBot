package queueapplication.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

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

@Service
@Scope("singleton")
public class BrokersInfo {
    private static final String BROKERS_XML_FILE_PATH = String.format("%s%s", System.getProperty("user.dir"), "\\src\\main\\resources\\static\\config\\brokers.xml");
    private final List<String> brokerList;

    public BrokersInfo(){
        this.brokerList = getBrokersAddresses();
    }

    public List<String> getList() {
        return brokerList;
    }

    private static List<String> getBrokersAddressesFromXML() {
        var builderFactory = DocumentBuilderFactory.newInstance();

        try {
            builderFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            var builder = builderFactory.newDocumentBuilder();

            var document = builder.parse(new File(BROKERS_XML_FILE_PATH));
            document.getDocumentElement().normalize();
            var nodeList = document.getElementsByTagName("broker");

            var brokersAddressesFromXML = new ArrayList<String>();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element broker = (Element) nodeList.item(i);
                if (broker.hasAttribute("address")) {
                    String brokerAddress = broker.getAttribute("address");
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

    private static boolean isBrokerAvailable(String brokerAddress) throws IOException {
        var brokerAddressURL = new URL(brokerAddress);
        HttpURLConnection connection = (HttpURLConnection) brokerAddressURL.openConnection();
        connection.setRequestMethod("GET");
        try{
            int responseCode = connection.getResponseCode();
            return responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_NOT_FOUND;  // ------------ handle the error or add to the log file ------------
        }
        catch (ConnectException e){
            return false;
        }
    }

    private static List<String> getBrokersAddresses() {
        try {
            var brokersAddressesFromXML = (ArrayList<String>) getBrokersAddressesFromXML();
            var brokersAddresses = new ArrayList<String>();

            for (String brokerAddress : brokersAddressesFromXML) {
                if (isBrokerAvailable(brokerAddress)) {
                    brokersAddresses.add(brokerAddress);
                }
            }
            return brokersAddresses;
        }
        catch (IOException e) {
            e.printStackTrace();                           // ------------ handle the error or add to the log file ------------
        }
        return new ArrayList<>();
    }
}
