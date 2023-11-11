package se.lexicon.todoapi.repository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import se.lexicon.todoapi.domain.entity.User;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    EntityManager entityManager;

    @Test
    void testUpdateExpiredByEmail() {

        User user = new User("test@test.com", "1234");
        user.setExpired(false);
        userRepository.save(user);

        entityManager.flush();
        entityManager.clear();

        userRepository.updateExpiredByEmail("test@test.com", true);

        userRepository.findById("test@test.com").ifPresent(role -> assertTrue(role.isExpired()));

    }
}