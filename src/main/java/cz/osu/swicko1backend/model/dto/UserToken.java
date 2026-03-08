package cz.osu.swicko1backend.model.dto;

import cz.osu.swicko1backend.model.entity.Role;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserToken {
    private String username;
    private Role role;
}