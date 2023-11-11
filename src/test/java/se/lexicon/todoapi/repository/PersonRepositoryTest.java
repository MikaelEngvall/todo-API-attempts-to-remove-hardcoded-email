package se.lexicon.todoapi.repository;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import se.lexicon.todoapi.domain.entity.Person;
import se.lexicon.todoapi.domain.entity.Role;
import se.lexicon.todoapi.domain.entity.Task;
import se.lexicon.todoapi.domain.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
//@ExtendWith(SpringExtension.class)
class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @Test
    void testFindPersonByTasksIsEmpty() {
        Person person = new Person();
        personRepository.save(person);

        Optional<Person> foundPerson = personRepository.findById(1L);
        foundPerson.ifPresent(found -> assertTrue(foundPerson.get().getTasks().isEmpty()));
    }
}
