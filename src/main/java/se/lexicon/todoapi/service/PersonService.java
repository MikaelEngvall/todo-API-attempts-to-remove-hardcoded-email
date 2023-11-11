package se.lexicon.todoapi.service;

import org.springframework.data.jpa.repository.Query;
import se.lexicon.todoapi.domain.dto.PersonDTOForm;
import se.lexicon.todoapi.domain.dto.PersonDTOView;
import se.lexicon.todoapi.domain.entity.Person;
import se.lexicon.todoapi.domain.entity.Task;

import java.util.List;

public interface PersonService {

    PersonDTOView create(PersonDTOForm personDTOForm);

    List<PersonDTOView> getAll();
}
