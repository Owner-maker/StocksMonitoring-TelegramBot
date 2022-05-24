package consumer.repository;

import consumer.models.TopicQueue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TopicQueueRepository extends JpaRepository<TopicQueue, Long> {
    List<TopicQueue> findAll();
}
