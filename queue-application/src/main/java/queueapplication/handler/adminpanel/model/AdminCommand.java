package queueapplication.handler.adminpanel.model;


public class AdminCommand {
    private final String commandCode;
    private final String topicName;
    private final boolean isCreatingInSingleBroker;
    private final int partitionQuantity;
    private final String singleBrokerAddress;

    public AdminCommand(String commandCode, String topicName,
                        boolean isCreatingInSingleBroker, int partitionQuantity, String singleBrokerAddress) {
        this.commandCode = commandCode;
        this.topicName = topicName;
        this.isCreatingInSingleBroker = isCreatingInSingleBroker;
        this.partitionQuantity = partitionQuantity;
        this.singleBrokerAddress = singleBrokerAddress;
    }

    public AdminCommand(String commandCode){
        this(commandCode,null,false,0,null);
    }

    public String getCommandCode() {
        return commandCode;
    }

    public String getTopicName() {
        return topicName;
    }

    public boolean isCreatingInSingleBroker() {
        return isCreatingInSingleBroker;
    }

    public int getPartitionQuantity() {
        return partitionQuantity;
    }

    public String getSingleBrokerAddress() {
        return singleBrokerAddress;
    }
}
