package queuemanager.pojo;

public class Consumer {
    private String addressURL;
    private int groupId;

    public Consumer() {
    }

    public Consumer(String addressURL, int groupId) {
        this.addressURL = addressURL;
        this.groupId = groupId;
    }

    public String getAddressURL() {
        return addressURL;
    }

    public void setAddressURL(String addressURL) {
        this.addressURL = addressURL;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
}
