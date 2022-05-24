package consumer.repository;

import consumer.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    List<User> findAll();

    @Override
    Optional<User> findById(Long aLong);

    @Override
    void deleteById(Long aLong);
}
