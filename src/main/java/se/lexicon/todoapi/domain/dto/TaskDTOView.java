package se.lexicon.todoapi.domain.dto;

import lombok.*;
import se.lexicon.todoapi.domain.entity.Person;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class TaskDTOView {

    private String title;
    private String description;
    private LocalDate deadline;
    private boolean done;
    private String userEmail;
    private String personName;
    private boolean userExpired;


    // View for person's task view
    public TaskDTOView(String title, String description, LocalDate deadline, boolean done, String userEmail, boolean userExpired) {
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.done = done;
        this.userEmail = userEmail;
        this.userExpired = userExpired;
    }
}