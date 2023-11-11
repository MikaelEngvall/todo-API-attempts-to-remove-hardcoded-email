package se.lexicon.todoapi.service;

import se.lexicon.todoapi.domain.dto.UserDTOForm;
import se.lexicon.todoapi.domain.dto.UserDTOView;

import java.util.List;

public interface UserService {

    UserDTOView register(UserDTOForm userDTOForm);
    UserDTOView getByEmail(String email);
    void disableByEmail(String email);
    void enableByEmail(String email);
    List<UserDTOView> getAll();
}
