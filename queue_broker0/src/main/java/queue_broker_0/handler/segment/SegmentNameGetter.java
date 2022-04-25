package queue_broker_0.handler.segment;

import org.springframework.stereotype.Component;
import queue_broker_0.handler.DataInput;
import queue_broker_0.pojo.SegmentInfo;

@Component
public class SegmentNameGetter implements DataInput<String, SegmentInfo> {

    private static final String SEGMENT_MOCK_NAME = "0.log";

    @Override
    public String getData(SegmentInfo inputData) {
        return SEGMENT_MOCK_NAME;
    }
}
