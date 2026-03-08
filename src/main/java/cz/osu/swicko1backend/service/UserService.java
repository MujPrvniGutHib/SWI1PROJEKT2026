package cz.osu.swicko1backend.service;

import cz.osu.swicko1backend.model.dto.UserToken;
import cz.osu.swicko1backend.model.entity.AppUser;
import cz.osu.swicko1backend.model.entity.Role;
import cz.osu.swicko1backend.model.repository.AppUserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final AppUserRepository userRepository;

    public UserService(AppUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String registerUser(String username, String password) {
        String ret;
        if (userRepository.existsByUsernameIgnoreCase(username)) {
            ret = "Username already taken!";
        } else {
            AppUser user = new AppUser();
            user.setUsername(username);
            // Tady by bylo šifrování hesla (BCrypt, add salt)
            user.setPassword(password);
            user.setRole(Role.USER);

            userRepository.save(user);
            ret = "User registered successfully!";
        }
        return ret;
    }

    public UserToken login(String username, String password) {
        AppUser user = userRepository.findByUsernameIgnoreCase(username);
        if (user != null) {
            if (user.getPassword().equals(password)) {
                return new UserToken(user.getUsername(), user.getRole());
            } else {
                throw new RuntimeException("Wrong password!");
            }
        } else {
            throw new IllegalArgumentException("Username does not exist!");
        }
    }
}
