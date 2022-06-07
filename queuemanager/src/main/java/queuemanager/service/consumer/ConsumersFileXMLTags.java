package queuemanager.service.consumer;

public enum ConsumersFileXMLTags {
    GROUP_ITEM_NAME("group"),
    GROUP_ID_ITEM("id"),
    GROUP_READING_TOPIC_ITEM("topic"),
    CONSUMER_ITEM_NAME("consumer"),
    CONSUMER_HOST("host"),
    CONSUMER_PORT("port");

    private final String tagName;

    ConsumersFileXMLTags(String tagName){
        this.tagName = tagName;
    }

    public String getTagName(){
        return tagName;
    }
}
