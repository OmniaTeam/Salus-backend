package com.omnia.salusbackend.controllers;

import com.omnia.salusbackend.entity.ERole;
import com.omnia.salusbackend.entity.UserEntity;
import com.omnia.salusbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;
    @GetMapping
    public ResponseEntity<List<UserEntity>> getUsers() {
        var roles = List.of(ERole.ADMIN);
        return ResponseEntity.ok().body(userService.getUsersForAdmin(roles));
    }
}
