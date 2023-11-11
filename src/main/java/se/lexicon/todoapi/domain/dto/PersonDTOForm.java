package se.lexicon.todoapi.domain.dto;

import lombok.*;
import se.lexicon.todoapi.domain.entity.Task;
import se.lexicon.todoapi.domain.entity.User;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

public class PersonDTOForm {

    private Long id;
    private String name;
    private User user;
    private List<Task> tasks = new ArrayList<>();
    private String userEmail;
    private boolean userExpired;
}
