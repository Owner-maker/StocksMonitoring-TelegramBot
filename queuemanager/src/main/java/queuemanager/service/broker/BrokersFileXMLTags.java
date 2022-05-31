package queuemanager.service.broker;

public enum BrokersFileXMLTags {
    BROKER_ITEM_NAME("broker"),
    BROKER_HOST("host"),
    BROKER_PORT("port");

    private final String tagName;

    BrokersFileXMLTags(String tagName){
        this.tagName = tagName;
    }

    public String getTagName(){
        return tagName;
    }
}
