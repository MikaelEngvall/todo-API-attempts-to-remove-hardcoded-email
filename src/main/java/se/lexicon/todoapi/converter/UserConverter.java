package se.lexicon.todoapi.converter;

import org.springframework.stereotype.Repository;
import se.lexicon.todoapi.domain.dto.UserDTOView;
import se.lexicon.todoapi.domain.entity.User;

@Repository
public interface UserConverter {

    UserDTOView toUserDTOView(User entity);

    User toUserEntity(UserDTOView dtoView);
}
