package se.lexicon.todoapi.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import se.lexicon.g46emailsenderapi.converter.EmailConverter;
import se.lexicon.g46emailsenderapi.domain.dto.EmailDTOForm;
import se.lexicon.g46emailsenderapi.domain.dto.EmailDTOView;
import se.lexicon.g46emailsenderapi.domain.entity.Email;
import se.lexicon.g46emailsenderapi.repository.EmailRepository;
import se.lexicon.g46emailsenderapi.service.EmailService;
import se.lexicon.g46emailsenderapi.service.EmailServiceImpl;
import se.lexicon.todoapi.converter.UserConverter;
import se.lexicon.todoapi.domain.dto.UserDTOForm;
import se.lexicon.todoapi.domain.dto.UserDTOView;
import se.lexicon.todoapi.domain.entity.Role;
import se.lexicon.todoapi.domain.entity.User;
import se.lexicon.todoapi.exception.DataNotFoundException;
import se.lexicon.todoapi.repository.RoleRepository;
import se.lexicon.todoapi.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@ComponentScan
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private UserConverter userConverter;
    private EmailService emailService;
    private EmailRepository emailRepository;
    private EmailConverter emailConverter;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, UserConverter userConverter, EmailService emailService, EmailRepository emailRepository, EmailConverter emailConverter) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userConverter = userConverter;
        this.emailService = emailService;
        this.emailRepository = emailRepository;
        this.emailConverter = emailConverter;
    }

    @Override
    @Transactional
    public UserDTOView register(UserDTOForm userDTOForm) { // test.test@.se , 123456 , [ADMIN,USER])
        if (userDTOForm == null) throw new IllegalArgumentException("User form is null.");

        boolean isExistEmail = userRepository.existsByEmail(userDTOForm.getEmail());
        if (isExistEmail) throw new IllegalArgumentException("Email is already exist");

        Set<Role> roleList = userDTOForm.getRoles()
                .stream()
                .map(
                        roleDTOForm -> roleRepository.findById(roleDTOForm.getId())
                                .orElseThrow(() -> new DataNotFoundException("Role is not valid.")))
                .collect(Collectors.toSet());

        User user = new User(userDTOForm.getEmail(), passwordEncoder.encode(userDTOForm.getPassword()));
        user.setRoles(roleList);

//      Creatng the email
        EmailDTOForm sentEmail = new EmailDTOForm(userDTOForm.getEmail(),
                "Registration Confirmed",
                "You've successfully registered you new user",
                1);

//        emailRepository.save(emailConverter.fromFormToEntity(sentEmail)); ?? TODO Doesn't work

//      Sending the email
        emailService.sendEmail(sentEmail);
//      Persisting the email
        User savedUser = userRepository.save(user);

        return userConverter.toUserDTOView(savedUser);
    }

    @Override
    public UserDTOView getByEmail(String email) {
        User foundUser = userRepository.findById(email).orElseThrow(() -> new DataNotFoundException("Email does not exist."));

        return userConverter.toUserDTOView(foundUser);
    }

    @Override
    @Transactional
    public void disableByEmail(String email) {
        isEmailTaken(email);
        userRepository.updateExpiredByEmail(email, true);
    }

    @Override
    @Transactional
    public void enableByEmail(String email) {
        isEmailTaken(email);
        userRepository.updateExpiredByEmail(email, false);
    }

    public void isEmailTaken(String email) {
        if (!userRepository.existsByEmail(email)) throw new DataNotFoundException("Email does not exist");
    }

    public List<UserDTOView> getAll() {
        List<User> allUsers = userRepository.getAll();
        return allUsers.stream()
                .map(user -> userConverter.toUserDTOView(user))
                .toList();
    }
}
