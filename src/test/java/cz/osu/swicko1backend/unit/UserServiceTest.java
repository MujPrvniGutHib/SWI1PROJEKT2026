package cz.osu.swicko1backend.unit;

import cz.osu.swicko1backend.model.dto.UserToken;
import cz.osu.swicko1backend.model.entity.AppUser;
import cz.osu.swicko1backend.model.entity.Role;
import cz.osu.swicko1backend.model.repository.AppUserRepository;
import cz.osu.swicko1backend.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private AppUserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void registerUser_WhenUsernameExists_ReturnsErrorMessage() {
        // Arrange
        String username = "existingUser";
        String password = "password";
        when(userRepository.existsByUsernameIgnoreCase(username)).thenReturn(true);

        // Act
        String result = userService.registerUser(username, password);

        // Assert
        assertEquals("Username already taken!", result);
        verify(userRepository, never()).save(any(AppUser.class));
    }

    @Test
    void registerUser_WhenUsernameDoesNotExist_SavesUserAndReturnsSuccessMessage() {
        // Arrange
        String username = "newUser";
        String password = "password";
        when(userRepository.existsByUsernameIgnoreCase(username)).thenReturn(false);

        // Act
        String result = userService.registerUser(username, password);

        // Assert
        assertEquals("User registered successfully!", result);
        verify(userRepository, times(1)).save(any(AppUser.class));
    }

    @Test
    void login_WhenUserExistsAndPasswordIsCorrect_ReturnsUserToken() {
        // Arrange
        String username = "testUser";
        String password = "correctPassword";
        AppUser user = new AppUser();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(Role.USER);

        when(userRepository.findByUsernameIgnoreCase(username)).thenReturn(user);

        // Act
        UserToken result = userService.login(username, password);

        // Assert
        assertNotNull(result);
        verify(userRepository, times(1)).findByUsernameIgnoreCase(username);
    }

    @Test
    void login_WhenUserExistsButPasswordIsWrong_ThrowsRuntimeException() {
        // Arrange
        String username = "testUser";
        String password = "wrongPassword";
        AppUser user = new AppUser();
        user.setUsername(username);
        user.setPassword("correctPassword");

        when(userRepository.findByUsernameIgnoreCase(username)).thenReturn(user);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> userService.login(username, password));
    }

    @Test
    void login_WhenUsernameDoesNotExist_ThrowsIllegalArgumentException() {
        // Arrange
        String username = "nonExistentUser";
        String password = "password";
        when(userRepository.findByUsernameIgnoreCase(username)).thenReturn(null);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> userService.login(username, password));
    }
}
