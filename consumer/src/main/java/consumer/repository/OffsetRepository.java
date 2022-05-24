package consumer.repository;

import consumer.models.Offset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OffsetRepository extends JpaRepository<List<Offset>,Long> {
    Optional<List<Offset>> findByUserId(Long aLong);

    Optional<Offset> findByUserIdAndTopicName(Long aLong, String topicName);
}
