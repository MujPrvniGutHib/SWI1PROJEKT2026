package cz.osu.swicko1backend.model.entity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class AppUser {
    private Long id;
    private String username;

    private String password;
    private Role role;


}
