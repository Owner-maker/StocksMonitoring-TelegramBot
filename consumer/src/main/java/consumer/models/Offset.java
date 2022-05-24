package consumer.models;

import javax.persistence.*;

@Entity
@Table(name = "offset")
public class Offset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "topic_id", referencedColumnName = "id")
    private TopicQueue topicQueue;

    public Offset(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TopicQueue getTopicQueue() {
        return topicQueue;
    }

    public void setTopicQueue(TopicQueue topicQueue) {
        this.topicQueue = topicQueue;
    }
}
