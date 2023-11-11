package se.lexicon.todoapi.converter;

import org.springframework.stereotype.Repository;
import se.lexicon.todoapi.domain.dto.RoleDTOView;
import se.lexicon.todoapi.domain.entity.Role;

@Repository
public interface RoleConverter {

    RoleDTOView toRoleDTOView(Role entity);

    Role toRoleEntity(RoleDTOView dtoView);
}
