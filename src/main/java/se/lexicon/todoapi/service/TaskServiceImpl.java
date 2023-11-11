package se.lexicon.todoapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.todoapi.converter.TaskConverter;
import se.lexicon.todoapi.domain.dto.TaskDTOForm;
import se.lexicon.todoapi.domain.dto.TaskDTOView;
import se.lexicon.todoapi.domain.entity.Person;
import se.lexicon.todoapi.domain.entity.Task;
import se.lexicon.todoapi.repository.PersonRepository;
import se.lexicon.todoapi.repository.TaskRepository;
import se.lexicon.todoapi.repository.UserRepository;

import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private TaskConverter taskConverter;
    private TaskRepository taskRepository;
    private PersonRepository personRepository;
    private UserRepository userRepository;

    @Autowired
    public TaskServiceImpl(TaskConverter taskConverter,
                           TaskRepository taskRepository,
                           PersonRepository personRepository,
                           UserRepository userRepository) {
        this.taskConverter = taskConverter;
        this.taskRepository = taskRepository;
        this.personRepository = personRepository;
        this.userRepository = userRepository;
    }

    public TaskDTOView saveTask(TaskDTOForm taskDTOForm, Long id) {
        if (taskDTOForm == null) throw new IllegalArgumentException("Task form is null.");

        Task task = new Task(taskDTOForm.getTitle(),
                taskDTOForm.getDescription(),
                taskDTOForm.getDeadline(),
                taskDTOForm.isDone());
        task.setUserEmail(taskDTOForm.getUserEmail());
        task.setUserExpired(taskDTOForm.isUserExpired());
        personRepository.findById(id).ifPresent(task::setPerson);

        Task savedTask = taskRepository.save(task);

        taskRepository.findById(savedTask.getId());

        return taskConverter.toTaskDTOView(savedTask);
    }

    @Override
    public List<TaskDTOView> getAll() {
        List<Task> allTasks = taskRepository.getAll();
        return allTasks.stream()
                .map(task -> taskConverter.toTaskDTOView(task))
                .toList();
    }
}
