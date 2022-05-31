package consumer.service;

import org.springframework.stereotype.Service;

@Service
public class ConsumerData {
    private int groupId;
    private boolean isLeaderGroup;
    private String groupLeaderAddress;

    public ConsumerData() {
        isLeaderGroup = false;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public boolean isLeaderGroup() {
        return isLeaderGroup;
    }

    public void setLeaderGroup(boolean leaderGroup) {
        isLeaderGroup = leaderGroup;
    }

    public String getGroupLeaderAddress() {
        return groupLeaderAddress;
    }

    public void setGroupLeaderAddress(String groupLeaderAddress) {
        this.groupLeaderAddress = groupLeaderAddress;
    }
}
