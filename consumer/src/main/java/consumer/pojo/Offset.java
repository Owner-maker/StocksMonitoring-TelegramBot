package consumer.pojo;

public class Offset {
    private String topicName;
    private int offset=0;

    public Offset(){
    }

    public Offset(String topicName) {
        this.topicName = topicName;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public void incrementOffset() {
        offset++;
    }
}
