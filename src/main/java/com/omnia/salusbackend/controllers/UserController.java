package com.omnia.salusbackend.controllers;

import com.omnia.salusbackend.dto.UserResponseDTO;
import com.omnia.salusbackend.entity.UserEntity;
import com.omnia.salusbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<?> getUser() {
        DefaultOidcUser userPrincipal = (DefaultOidcUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        UserEntity user = userService.getUserByEmail(userPrincipal.getEmail());
        return ResponseEntity.ok().body(new UserResponseDTO(user));
    }
}
