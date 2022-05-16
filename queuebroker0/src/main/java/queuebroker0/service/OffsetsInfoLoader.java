package queuebroker0.service;

import org.springframework.stereotype.Service;
import queuebroker0.pojo.user.Offset;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OffsetsInfoLoader {
    private final Map<String, List<Offset>> offsets;

    public OffsetsInfoLoader(){
        offsets = new HashMap<>();
        List<Offset> offsetsForExample = new ArrayList<>();
        offsetsForExample.add(new Offset("Test"));
        offsets.put("TestGroupId",offsetsForExample);
    }

    public void incrementOffsetOfGroup(String topicName,String groupId){
        offsets.get(groupId).stream().filter(offset -> offset.getTopicName().equals(topicName)).findFirst().get().incrementOffset();
    }

    public Map<String, List<Offset>> getOffsets() {
        return new HashMap<>(offsets);
    }
}