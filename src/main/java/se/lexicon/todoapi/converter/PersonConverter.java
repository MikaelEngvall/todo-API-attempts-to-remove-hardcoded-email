package se.lexicon.todoapi.converter;

import org.springframework.stereotype.Repository;
import se.lexicon.todoapi.domain.dto.PersonDTOView;
import se.lexicon.todoapi.domain.entity.Person;

@Repository
public interface PersonConverter {

    PersonDTOView toPersonDTOView(Person entity);
    PersonDTOView toPersonsTaskDTOView(Person entity);

    Person toPersonEntity(PersonDTOView dtoView);
}
