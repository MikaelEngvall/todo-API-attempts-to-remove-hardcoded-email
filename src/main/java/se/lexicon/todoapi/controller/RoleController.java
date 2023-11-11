package se.lexicon.todoapi.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.lexicon.todoapi.converter.UserConverter;
import se.lexicon.todoapi.domain.dto.RoleDTOView;
import se.lexicon.todoapi.domain.dto.UserDTOView;
import se.lexicon.todoapi.domain.entity.User;
import se.lexicon.todoapi.repository.UserRepository;
import se.lexicon.todoapi.service.RoleService;

import java.util.List;

@RequestMapping("/api/v1/roles")
@RestController
public class RoleController {

    private final RoleService roleService;
    private final UserRepository userRepository;
    private final UserConverter userConverter;

    @Autowired
    public RoleController(RoleService roleService, UserRepository userRepository, UserConverter userConverter) {
        this.roleService = roleService;
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }

    @GetMapping("/")
    public ResponseEntity<List<RoleDTOView>> doGetRoles() {
        List<RoleDTOView> responseBody = roleService.getAll();

        return ResponseEntity.ok().body(responseBody);
    }

    @GetMapping("/getall")
    public List<UserDTOView> getAll() {
        List<User> allUsers = userRepository.getAll();
        return allUsers.stream()
                .map(userConverter::toUserDTOView)
                .toList();
    }
}
