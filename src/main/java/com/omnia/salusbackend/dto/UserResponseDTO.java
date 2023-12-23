package com.omnia.salusbackend.dto;

import com.omnia.salusbackend.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponseDTO {
    Long id;
    String fio;
    String email;
    String role;

    public UserResponseDTO(UserEntity user) {
        this.id = user.getId();
        this.fio = user.getFio();
        this.email = user.getEmail();
        this.role = user.getRole().name();
    }
}
