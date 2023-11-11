package se.lexicon.todoapi.domain.dto;

import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class RoleDTOView {

    private Long id;
    private String name;
}
