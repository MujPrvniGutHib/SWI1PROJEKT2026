package cz.osu.swicko1backend.controller;

import cz.osu.swicko1backend.model.dto.LoginRequest;
import cz.osu.swicko1backend.model.dto.RegistrationRequest;
import cz.osu.swicko1backend.model.dto.UserToken;
import cz.osu.swicko1backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/register", produces = "application/json", method = RequestMethod.POST)
    public ResponseEntity<String> registerUser(@RequestBody RegistrationRequest registrationDTO) {
        String ret = userService.registerUser(registrationDTO.getUsername(), registrationDTO.getPassword());

        return new ResponseEntity<>(ret, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> loginUser(@RequestBody LoginRequest loginDTO) {
        try {
            UserToken userToken = userService.login(loginDTO.getUsername(), loginDTO.getPassword());
            return new ResponseEntity<>(userToken, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }
}
