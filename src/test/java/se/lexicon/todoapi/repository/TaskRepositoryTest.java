package se.lexicon.todoapi.repository;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Query;
import se.lexicon.todoapi.domain.entity.Person;
import se.lexicon.todoapi.domain.entity.Task;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private PersonRepository personRepository;

    @Test
    void testFindByTitleContainsIgnoreCase() {
        Task task = new Task(); // inside
        Task task2 = new Task(); // outside
        Task task3 = new Task(); // rim
        task.setTitle("title");
        task2.setTitle("qwerty");
        task3.setTitle("tl");

        taskRepository.save(task);
        assertEquals(1, taskRepository.findByTitleContainsIgnoreCase("iTl").size());
    }

    @Test
    void testFindByPerson_Id() {
        Person person = new Person();
        personRepository.save(person);

        Person foundPerson = personRepository.findAll().get(0);
        Long foundPersonId = foundPerson.getId();

        Task task = new Task();
        task.setPerson(foundPerson);
        taskRepository.save(task);

        assertEquals(1, taskRepository.findTasksByPerson_Id(foundPersonId).size());
    }

    @Test
    void testFindByDone() {
        Task task = new Task();
        task.setDone(true);
        taskRepository.save(task);
        assertEquals(1, taskRepository.findByDone(true).size());
    }

    @Test
    void testFindByDeadLineBetween() {
        Task task1 = new Task(); // inside
        Task task2 = new Task(); // rim
        Task task3 = new Task(); // outside
        task1.setDeadline(LocalDate.now());
        task2.setDeadline(LocalDate.now().plusDays(1));
        task3.setDeadline(LocalDate.now().plusDays(2));
        taskRepository.save(task1);
        taskRepository.save(task2);
        taskRepository.save(task3);

        LocalDate from = LocalDate.now();
        LocalDate to = LocalDate.now().plusDays(1);

        assertEquals(2, taskRepository.findByDeadlineBetween(from, to).size());
    }

    @Test
    void testFindByDeadline() {
        Task task = new Task();
        task.setDeadline(LocalDate.now());
        taskRepository.save(task);

        assertEquals(1, taskRepository.findByDeadline(LocalDate.now()).size());
    }

    @Test
    void testFindByPersonNull() {
        Person person = new Person("name");
        personRepository.save(person);

        Task task1 = new Task();
        Task task2 = new Task();
        task2.setPerson(person);
        taskRepository.save(task1);
        taskRepository.save(task2);

        assertEquals(1, taskRepository.findByPersonNull().size());
    }

    @Test
    void testFindByPersonIsNotNull() {
        Person person = new Person();
        personRepository.save(person);

        Task task1 = new Task();
        Task task2 = new Task();
        task1.setPerson(person);
        taskRepository.save(task1);
        taskRepository.save(task2);

        assertEquals(1, taskRepository.findByPersonNotNull().size());
    }

    @Test
    void findByDoneFalse() {
        Task task1 = new Task();
        Task task2 = new Task();
        task2.setDone(true);
        taskRepository.save(task1);
        taskRepository.save(task2);

        assertEquals(1, taskRepository.findByDoneFalse().size());
    }

    @Test
    void testFindByDoneFalseAndDeadlineAfter() {
        Task task1 = new Task();
        Task task2 = new Task();
        Task task3 = new Task();
        Task task4 = new Task();

        task1.setDeadline(LocalDate.now());
        task1.setDone(true);

        task2.setDeadline(LocalDate.now()); // false

        task3.setDeadline(LocalDate.now().minusDays(1));
        task3.setDone(true);

        task4.setDeadline(LocalDate.now().minusDays(1)); // false

        taskRepository.save(task1);
        taskRepository.save(task2);
        taskRepository.save(task3);
        taskRepository.save(task4);

        assertEquals(1, taskRepository.findByDoneFalseAndDeadlineBefore(LocalDate.now()).size());
    }
}