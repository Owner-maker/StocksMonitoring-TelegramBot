package producer.pojo;

public class Topic {
    private String name;
    private int partitionQuantity;

    public Topic(){
    }

    public Topic(String name, int partitionQuantity) {
        this.name = name;
        this.partitionQuantity = partitionQuantity;
    }

    public String getName() {
        return name;
    }

    public int getPartitionQuantity() {
        return partitionQuantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPartitionQuantity(int partitionQuantity) {
        this.partitionQuantity = partitionQuantity;
    }
}
