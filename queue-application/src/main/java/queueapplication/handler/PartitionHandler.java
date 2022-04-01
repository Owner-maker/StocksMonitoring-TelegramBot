package queueapplication.handler;

import queueapplication.exception.FileWriteException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class PartitionHandler {

    private PartitionHandler() {
    }

    public static void createPartitionsInTopic(String topicName, int amountOfPartitions) throws IOException {
        var pathToTopic = Paths.get(String.format("%s\\%s", QueueHandler.CURRENT_USER_LOGS_DIRECTORY, topicName));

        if (Files.exists(pathToTopic)) {
            Stream<Path> stream = Files.list(pathToTopic);
            int amountOfExistPartitions = (int)stream.count();
            stream.close();

            for (int i = amountOfExistPartitions; i < amountOfExistPartitions + amountOfPartitions; i++) {
                var partitionDirectory = new File(String.format("%s\\%d",String.valueOf(pathToTopic),i));
                partitionDirectory.mkdir();      //     ------ handle the possible exceptions in further ------
            }
        }
    }
}
