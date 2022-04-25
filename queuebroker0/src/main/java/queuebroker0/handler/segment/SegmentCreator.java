package queuebroker0.handler.segment;

import org.springframework.stereotype.Component;
import queuebroker0.handler.DataOutput;
import queuebroker0.pojo.SegmentInfo;
import queuebroker0.service.BrokerInfoLoader;

import java.io.File;
import java.io.IOException;

@Component
public class SegmentCreator implements DataOutput<Boolean, SegmentInfo> {

    private final SegmentNameGetter segmentNameGet;

    public SegmentCreator(SegmentNameGetter segmentNameGet) {
        this.segmentNameGet = segmentNameGet;
    }

    @Override
    public Boolean create(SegmentInfo infoCreation) {
        try{
            String pathToSegment = String.format("%s\\%s\\%d\\%s",BrokerInfoLoader.LOGS_DIRECTORY_PATH ,
                    infoCreation.getTopicName(), infoCreation.getPartitionNumber(),segmentNameGet.getData(infoCreation));
            var file = new File(pathToSegment);
            return file.createNewFile();
        }
        catch(IOException e){
            return false;
        }
    }
}
