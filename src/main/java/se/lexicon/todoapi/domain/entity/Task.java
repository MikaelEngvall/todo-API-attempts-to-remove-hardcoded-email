package se.lexicon.todoapi.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode(exclude = "person")
@ToString(exclude = "person")
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
public class Task {

    // TODO: 05/11/2023 keep this?
    public Task(String title, String description, LocalDate deadline, boolean done) {
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.done = done;
    }

    public Task(String title, String description, LocalDate deadline, boolean done, Person person) {
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.done = done;
        this.person = person;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private LocalDate deadline;
    private boolean done;
    private String userEmail;
    private String personName;
    private boolean userExpired;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

}
