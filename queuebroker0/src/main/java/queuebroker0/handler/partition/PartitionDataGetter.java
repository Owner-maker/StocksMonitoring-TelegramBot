package queuebroker0.handler.partition;

import org.springframework.stereotype.Component;
import queuebroker0.handler.DataInput;
import queuebroker0.pojo.partition.Partition;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Component
public class PartitionDataGetter implements DataInput<List<Partition>, String> {
    @Override
    public List<Partition> getData(String pathToTopic) {
        var partitions = new ArrayList<Partition>();

        try(var streamPartitionsPaths = Files.list(Path.of(pathToTopic))){
            streamPartitionsPaths
                    .forEach(path -> partitions.add(
                            new Partition(Integer.parseInt(path.getFileName().toString()))));
            return partitions;
        }
        catch (IOException e) {
            return new ArrayList<>();
        }
    }
}
