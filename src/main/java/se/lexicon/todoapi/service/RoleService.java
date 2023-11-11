package se.lexicon.todoapi.service;

import se.lexicon.todoapi.domain.dto.RoleDTOView;
import se.lexicon.todoapi.domain.entity.Role;

import java.util.List;

public interface RoleService {

    List<RoleDTOView> getAll();
}
