package cz.osu.swicko1backend.controller;

import cz.osu.swicko1backend.model.dto.RegistrationRequest;
import cz.osu.swicko1backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/register", produces = "application/json", method = RequestMethod.POST)
    public ResponseEntity<String> registerUser(@RequestBody RegistrationRequest registrationRequest) {
        String ret = userService.registerUser(registrationDTO.getUsername(), registrationDTO.getPassword)
    }
}
