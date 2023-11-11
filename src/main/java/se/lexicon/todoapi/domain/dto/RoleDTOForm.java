package se.lexicon.todoapi.domain.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

public class RoleDTOForm {

    private Long id;
    private String name;
}
