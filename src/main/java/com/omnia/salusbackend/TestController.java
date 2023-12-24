package com.omnia.salusbackend;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping
public class TestController {
//    @GetMapping
//    public ResponseEntity<?> test() {
//        DefaultOidcUser user = (DefaultOidcUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        return ResponseEntity.ok().body(user.getFullName());
//    }
@GetMapping
public ResponseEntity<?> test() {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    if (principal instanceof DefaultOidcUser user) {
        return ResponseEntity.ok().body(user.getFullName());
    } else {
        // Обработка, когда Principal не является объектом DefaultOidcUser
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
}
