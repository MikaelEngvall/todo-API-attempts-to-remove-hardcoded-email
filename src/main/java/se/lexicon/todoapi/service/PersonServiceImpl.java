package se.lexicon.todoapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.todoapi.converter.PersonConverter;
import se.lexicon.todoapi.domain.dto.PersonDTOForm;
import se.lexicon.todoapi.domain.dto.PersonDTOView;
import se.lexicon.todoapi.domain.dto.TaskDTOForm;
import se.lexicon.todoapi.domain.dto.UserDTOView;
import se.lexicon.todoapi.domain.entity.Person;
import se.lexicon.todoapi.domain.entity.Task;
import se.lexicon.todoapi.domain.entity.User;
import se.lexicon.todoapi.exception.DataNotFoundException;
import se.lexicon.todoapi.repository.PersonRepository;
import se.lexicon.todoapi.repository.TaskRepository;
import se.lexicon.todoapi.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    private PersonRepository personRepository;
    private PersonConverter personConverter;
    private TaskRepository taskRepository;
    private UserRepository userRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository,
                             PersonConverter personConverter,
                             TaskRepository taskRepository,
                             UserRepository userRepository) {
        this.personRepository = personRepository;
        this.personConverter = personConverter;
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @Override
    public PersonDTOView create(PersonDTOForm personDTOForm) {
        if (personDTOForm == null) throw new IllegalArgumentException("Person form is null.");

        Person person = new Person(personDTOForm.getName(),
                userRepository.getUserByEmail(personDTOForm.getUserEmail()),
                personDTOForm.getTasks(),
                personDTOForm.getUserEmail(),
                personDTOForm.isUserExpired());

        Person savedPerson = personRepository.save(person);

        return personConverter.toPersonDTOView(savedPerson);
    }

    public List<PersonDTOView> getAll() {
        List<Person> allPersons = personRepository.findAll();
        return allPersons.stream()
                .map(person -> personConverter.toPersonsTaskDTOView(person))
                .toList();
    }
}
