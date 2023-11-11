package se.lexicon.todoapi.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.lexicon.todoapi.domain.dto.PersonDTOView;
import se.lexicon.todoapi.domain.dto.TaskDTOView;
import se.lexicon.todoapi.domain.entity.Task;
import se.lexicon.todoapi.repository.PersonRepository;

import java.time.LocalDate;

@Component
public class TaskConverterImpl implements TaskConverter{

    private final PersonRepository personRepository;

    @Autowired
    public TaskConverterImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public TaskDTOView toTaskDTOView(Task entity) {
        return TaskDTOView.builder()
                .title(entity.getTitle())
                .description(entity.getDescription())
                .deadline(entity.getDeadline())
                .done(entity.isDone())
                .build();
    }
//    @Override
//    // person's task view
//    public TaskDTOView toTaskPersonDTOView(Task entity) {
//        return TaskDTOView.builder()
//                .title(entity.getTitle())
//                .description(entity.getDescription())
//                .deadline(entity.getDeadline())
//                .done(entity.isDone())
//                .build();
//    }

    @Override
    public Task toTaskEntity(TaskDTOView dtoView) {
        return Task.builder()
                .title(dtoView.getTitle())
                .description(dtoView.getDescription())
                .deadline(dtoView.getDeadline())
                .done(dtoView.isDone())
                .build();
    }
}
