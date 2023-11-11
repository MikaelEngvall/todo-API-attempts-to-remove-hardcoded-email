package se.lexicon.todoapi.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.todoapi.domain.entity.Role;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;

    @Test
    void testFindByName() {
        Optional<Role> foundRole = roleRepository.findByName("ADMIN");
        foundRole.ifPresent(role -> assertEquals("ADMIN", role.getName()));
    }
}