package queuebroker0.pojo;

import queuebroker0.pojo.partition.Partition;

import java.util.ArrayList;
import java.util.List;

public class Topic {
    private String name;
    private List<Partition> partitions;

    public Topic(){
    }

    public Topic(String name, List<Partition> partitions) {
        this.name = name;
        this.partitions = new ArrayList<>(partitions);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Partition> getPartitions() {
        return new ArrayList<>(partitions);
    }

    public void setPartitions(List<Partition> partitions) {
        this.partitions = new ArrayList<>(partitions);
    }
}

