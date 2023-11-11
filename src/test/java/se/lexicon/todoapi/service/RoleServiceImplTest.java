package se.lexicon.todoapi.service;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.todoapi.domain.dto.RoleDTOView;
import se.lexicon.todoapi.domain.entity.Role;
import se.lexicon.todoapi.repository.RoleRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class RoleServiceImplTest {

    @Autowired
    private RoleServiceImpl roleService;

    @Autowired
    private RoleRepository roleRepository;

    @Test
    void testGetAll() {
        List<RoleDTOView> foundRoleDTOView = roleService.getAll();

        int expectedValue = 3;
        int actualValue = foundRoleDTOView.size();

        assertEquals(expectedValue, actualValue);
    }
}