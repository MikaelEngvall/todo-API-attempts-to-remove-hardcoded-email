package se.lexicon.todoapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.todoapi.domain.dto.TaskDTOForm;
import se.lexicon.todoapi.domain.dto.TaskDTOView;
import se.lexicon.todoapi.repository.PersonRepository;
import se.lexicon.todoapi.service.PersonService;
import se.lexicon.todoapi.service.TaskService;

import java.util.List;

// TODO: 06/11/2023 do this
@RequestMapping("/api/v1/tasks")
@RestController
public class TaskController {

    private final TaskService taskService;
    private final PersonRepository personRepository;

    @Autowired
    public TaskController(TaskService taskService,
                          PersonRepository personRepository) {
        this.taskService = taskService;
        this.personRepository = personRepository;
    }

    @PostMapping("/")
    public ResponseEntity<TaskDTOView> doSaveTask(@RequestBody TaskDTOForm taskDTOForm) {
        TaskDTOView responseBody = taskService.saveTask(taskDTOForm, personRepository.findById(1L).get().getId());

        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<TaskDTOView>> doGetAll() {
        List<TaskDTOView> responseBody = taskService.getAll();
        return ResponseEntity.ok().body(responseBody);
    }
}
