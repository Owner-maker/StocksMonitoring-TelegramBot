package queuenode0.handler;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import queuenode0.pojo.Message;
import queuenode0.service.BrokerInfoLoader;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
@Scope("singleton")
public class SegmentHandler {

    public void addMessage(Message message) throws IOException {
        String topicName = message.getTopicName();
        int partitionNumber = message.getPartitionNumber();
        String pathToSegment = String.format("%s\\%s\\%s\\0.log",BrokerInfoLoader.LOGS_DIRECTORY_PATH,topicName,String.valueOf(partitionNumber));
        try(FileWriter writer = new FileWriter(pathToSegment,true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer)){

            bufferedWriter.write(message.toString());
        }
    }

    public void createSegment(String topicName, int partitionNumber) throws IOException {
        String pathToSegment = String.format("%s\\%s\\%s\\0.log",BrokerInfoLoader.LOGS_DIRECTORY_PATH,topicName,String.valueOf(partitionNumber));
        File file = new File(pathToSegment);
        file.createNewFile();
    }

    public String getMessage(String topicName, int partitionNumber, int offset){
        String pathToSegment = String.format("%s\\%s\\%s\\0.log",BrokerInfoLoader.LOGS_DIRECTORY_PATH,topicName,String.valueOf(partitionNumber));
        try(var fileReader = new FileReader(pathToSegment);
            var bufferedReader = new BufferedReader(fileReader)){
            return bufferedReader.lines().skip((long)(offset)).findFirst().orElseThrow();
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
