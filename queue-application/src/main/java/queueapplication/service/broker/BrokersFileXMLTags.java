package queueapplication.service.broker;

public enum BrokersFileXMLTags {
    BROKER_ITEM_NAME("broker"),
    BROKER_ADDRESS_ITEM_NAME("addressURL");

    private final String tagName;

    BrokersFileXMLTags(String tagName){
        this.tagName = tagName;
    }

    public String getTagName(){
        return tagName;
    }
}
