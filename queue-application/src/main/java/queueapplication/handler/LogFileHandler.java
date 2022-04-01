package queueapplication.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import queueapplication.pojo.Consumer;
import queueapplication.pojo.Message;
import queueapplication.service.Queue;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class LogFileHandler {
    private LogFileHandler() {
    }

    public static void createSegmentForConsumer(Queue queue, Consumer consumer,String topicName) throws IOException {
        var pathToTopic = Paths.get(String.format("%s\\%s", QueueHandler.CURRENT_USER_LOGS_DIRECTORY, topicName));

        var segmentFile = new File(String.format("%s\\%s\\0.log",String.valueOf(pathToTopic),consumer.getId()));
        segmentFile.createNewFile();      //     ------ handle the possible exceptions in further ------
    }

    public static void storeMessageInTopic(Message message, int offset, Consumer consumer) throws IOException {
        var pathToSegment = Paths.get(String.format("%s\\%s\\%s\\0.log", QueueHandler.CURRENT_USER_LOGS_DIRECTORY,
                message.getTopicName(),message.getKey()));
        File file = new File(String.valueOf(pathToSegment));

        try(FileWriter fileWriter = new FileWriter(file,true)){
            fileWriter.append(String.format("offset: %d keyvalue: %s value: %s timestamp: %s",offset,message.getKey(), message.getValue(),message.getTimestamp()));
            fileWriter.append("\n");
            fileWriter.flush();
        }
        catch (IOException ioException){

        }
    }
}
