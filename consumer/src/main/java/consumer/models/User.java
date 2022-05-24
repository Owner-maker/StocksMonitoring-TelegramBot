package consumer.models;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public User(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        id = id;
    }
}
