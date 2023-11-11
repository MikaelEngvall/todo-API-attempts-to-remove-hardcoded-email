package se.lexicon.todoapi.startup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import se.lexicon.todoapi.domain.entity.Person;
import se.lexicon.todoapi.domain.entity.Role;
import se.lexicon.todoapi.domain.entity.Task;
import se.lexicon.todoapi.repository.PersonRepository;
import se.lexicon.todoapi.repository.RoleRepository;
import se.lexicon.todoapi.repository.TaskRepository;

@Component
public class RoleDataLoader implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private PersonRepository personRepository;

    @Override
    public void run(String... args) throws Exception {
        // execute this logic ...

        //how to call save method of RoleRepository Interface?
        roleRepository.save(new Role("ADMIN"));
        roleRepository.save(new Role("USER"));
        roleRepository.save(new Role("GUEST"));
        // add more roles if needed
    }
}
