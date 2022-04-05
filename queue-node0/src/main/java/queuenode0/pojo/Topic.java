package queuenode0.pojo;

public class Topic {
    private String name;
    private int quantityOfPartitions;

    public Topic(String name, int quantityOfPartitions) {
        this.name = name;
        this.quantityOfPartitions = quantityOfPartitions;
    }

    public String getName() {
        return name;
    }

    public int getQuantityOfPartitions() {
        return quantityOfPartitions;
    }
}
