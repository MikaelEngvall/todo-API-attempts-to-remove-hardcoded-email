package se.lexicon.todoapi.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.lexicon.todoapi.domain.dto.RoleDTOView;
import se.lexicon.todoapi.domain.dto.UserDTOView;
import se.lexicon.todoapi.domain.entity.User;

import java.util.stream.Collectors;

@Component
public class UserConverterImpl implements UserConverter {

    @Autowired
    private RoleConverter roleConverter;

    @Override
    public UserDTOView toUserDTOView(User entity) {
        if (entity == null) {
            return null;
        }

        UserDTOView userDTOView = new UserDTOView();
        userDTOView.setEmail(entity.getEmail());

        userDTOView.setRoles(entity.getRoles().stream()
                .map(roleConverter::toRoleDTOView)
                .collect(Collectors.toSet()));

        return userDTOView;
    }

    @Override
    public User toUserEntity(UserDTOView dtoView) {
        if (dtoView == null) {
            return null;
        }

        User user = new User();
        user.setEmail(dtoView.getEmail());

        user.setRoles(dtoView.getRoles().stream()
                .map(roleConverter::toRoleEntity)
                .collect(Collectors.toSet()));

        return user;
    }
}
