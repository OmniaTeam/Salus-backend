package com.example.salusbackend.config.security;

import java.io.IOException;


import com.example.salusbackend.Service.UserService;
import com.example.salusbackend.entity.ERole;
import com.example.salusbackend.entity.UserEntity;
import com.example.salusbackend.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomSuccessHandler implements AuthenticationSuccessHandler{


    private final UserRepository userRepo;

    private final UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        String redirectUrl = "/";
        if(authentication.getPrincipal() instanceof DefaultOAuth2User userDetails) {
            var tmp = authentication.getPrincipal();
            DefaultOAuth2User userDetails234 = (DefaultOAuth2User ) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String email = userDetails.getAttribute("email");
            if(userRepo.findByEmail(email) == null) {
                UserEntity user = new UserEntity();
                user.setEmail(email);
                user.setFio(userDetails.getAttribute("name"));
                user.setRole(ERole.WORKER);
                userRepo.save(user);
            }
        }
        new DefaultRedirectStrategy().sendRedirect(request, response, redirectUrl);
    }

}
