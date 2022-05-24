package queuemanager.service.consumer;

public enum ConsumersFileXMLTags {
    GROUP_ITEM_NAME("group"),
    CONSUMER_ITEM_NAME("consumer"),
    BROKER_ADDRESS_ITEM_NAME("addressURL");

    private final String tagName;

    ConsumersFileXMLTags(String tagName){
        this.tagName = tagName;
    }

    public String getTagName(){
        return tagName;
    }
}
