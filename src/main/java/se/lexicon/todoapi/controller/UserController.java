package se.lexicon.todoapi.controller;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import se.lexicon.todoapi.domain.dto.UserDTOForm;
import se.lexicon.todoapi.domain.dto.UserDTOView;
import se.lexicon.todoapi.service.UserService;

import java.util.List;
@Controller
@RequestMapping("/api/v1/users")
//@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public ResponseEntity<UserDTOView> doGetUserByEmail(@RequestParam String email) {
        System.out.println(">>>>>>>> getUserByEmail has been executed!");
        System.out.println("Email: " + email);

        UserDTOView responseBody = userService.getByEmail(email);

        return ResponseEntity.ok().body(responseBody);
    }

    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";
    }

    @GetMapping("/getall")
    public ResponseEntity<List<UserDTOView>> doGetAllUsers() {
        List<UserDTOView> responseBody = userService.getAll();
        return ResponseEntity.ok().body(responseBody);
    }


    @PostMapping("/")
    public ResponseEntity<UserDTOView> doRegister(@RequestBody UserDTOForm userDTOForm) {
        System.out.println("DTO FORM: " + userDTOForm);

        UserDTOView responseBody = userService.register(userDTOForm);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }
    @Controller
    public class RegistrationSuccessController {

        @GetMapping("/api/v1/users/registration-success")
        public String showRegistrationSuccessPage() {
            return "registration-success";
        }
    }

    @PutMapping("/disable")
    @Transactional
    public ResponseEntity<Void> doDisableUserByEmail(@RequestParam("email") String email) {
        userService.disableByEmail(email);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @PutMapping("/enable")
    @Transactional
    public ResponseEntity<Void> doEnableUserByEmail(@RequestParam("email") String email) {
        userService.enableByEmail(email);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
