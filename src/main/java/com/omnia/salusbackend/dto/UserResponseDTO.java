package com.omnia.salusbackend.dto;

import com.omnia.salusbackend.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponseDTO {
    Long id;
    String fio;
    String login;
    String role;

    public UserResponseDTO(UserEntity user) {
        this.id = user.getId();
        this.fio = user.getFio();
        this.login = user.getLogin();
        this.role = user.getRole().name();
    }
}
