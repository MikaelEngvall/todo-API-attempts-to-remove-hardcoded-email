package se.lexicon.todoapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.todoapi.domain.dto.PersonDTOForm;
import se.lexicon.todoapi.domain.dto.PersonDTOView;
import se.lexicon.todoapi.domain.dto.TaskDTOForm;
import se.lexicon.todoapi.domain.dto.UserDTOView;
import se.lexicon.todoapi.repository.UserRepository;
import se.lexicon.todoapi.service.PersonService;

import java.util.List;

@RequestMapping("/api/v1/persons")
@RestController
public class PersonController {

    private final PersonService personService;
    private final UserRepository userRepository;

    @Autowired
    public PersonController(PersonService personService,
                            UserRepository userRepository) {
        this.personService = personService;
        this.userRepository = userRepository;
    }

    @PostMapping("/")
    public ResponseEntity<PersonDTOView> doCreate(@RequestBody PersonDTOForm personDTOForm) {

        PersonDTOView responseBody = personService.create(personDTOForm);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);

        // TODO: 06/11/2023 do this
    }

    @GetMapping("/getall")
    public ResponseEntity<List<PersonDTOView>> doGetAll() {
        List<PersonDTOView> responseBody = personService.getAll();
        return ResponseEntity.ok().body(responseBody);
    }
}
