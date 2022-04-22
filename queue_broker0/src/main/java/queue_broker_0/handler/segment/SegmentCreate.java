package queue_broker_0.handler.segment;

import org.springframework.stereotype.Component;
import queue_broker_0.handler.DataOutput;
import queue_broker_0.pojo.SegmentInfoCreation;
import queue_broker_0.service.BrokerInfoLoader;

import java.io.File;
import java.io.IOException;

@Component
public class SegmentCreate implements DataOutput<Boolean,String, SegmentInfoCreation> {
    @Override
    public Boolean create(String destination, SegmentInfoCreation infoCreation) {
        try{
            String pathToSegment = String.format("%s\\%s\\%d\\%s",BrokerInfoLoader.LOGS_DIRECTORY_PATH ,
                    infoCreation.getTopicName(), infoCreation.getPartitionNumber(),destination);
            var file = new File(pathToSegment);
            return file.createNewFile();
        }
        catch(IOException e){
            return false;
        }
    }
}
