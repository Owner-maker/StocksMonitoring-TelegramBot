package queuebroker0.handler.segment;

import org.springframework.stereotype.Component;
import queuebroker0.handler.DataInput;
import queuebroker0.pojo.SegmentInfo;

@Component
public class SegmentNameGetter implements DataInput<String, SegmentInfo> {

    private static final String SEGMENT_MOCK_NAME = "0.log";

    @Override
    public String getData(SegmentInfo inputData) {
        return SEGMENT_MOCK_NAME;
    }
}
