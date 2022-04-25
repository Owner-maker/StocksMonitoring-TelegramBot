package queue_broker_0.service.message_parser;

public enum MessageAttributes {
    KEY(1),
    TIMESTAMP(2),
    VALUE(3);

    private int groupNumber;

    MessageAttributes(int groupNumber) {
        this.groupNumber = groupNumber;
    }

    public int getGroupNumber() {
        return groupNumber;
    }
}
