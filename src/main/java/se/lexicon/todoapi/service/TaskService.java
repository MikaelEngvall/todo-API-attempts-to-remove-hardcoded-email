package se.lexicon.todoapi.service;


import se.lexicon.todoapi.domain.dto.TaskDTOForm;
import se.lexicon.todoapi.domain.dto.TaskDTOView;
import se.lexicon.todoapi.domain.entity.Task;

import java.util.List;

public interface TaskService {

    List<TaskDTOView> getAll();

    TaskDTOView saveTask(TaskDTOForm taskDTOForm, Long id);
}
