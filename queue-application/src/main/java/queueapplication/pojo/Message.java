package queueapplication.pojo;

public class Message {
    private String message;
    private String topicName;
    private int offset;
    private int valueSize;

    public Message(String message, String topicName) {
        this.message = message;
        this.topicName = topicName;
    }

    public String getMessage() {
        return message;
    }

    public String getTopicName() {
        return topicName;
    }

    public int getOffset() {
        return offset;
    }

    public int getValueSize() {
        return valueSize;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    @Override
    public String toString() {
        return String.format("offset:%d  message:%s ",offset,message);
    }
}
