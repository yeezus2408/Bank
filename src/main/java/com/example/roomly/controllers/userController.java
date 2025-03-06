package com.example.roomly.controllers;

import com.example.roomly.Entities.Enum.roles;
import com.example.roomly.Entities.users;
import com.example.roomly.services.CustomUserDetailsService;
import com.example.roomly.services.userService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class userController {
    private final PasswordEncoder passwordEncoder;
    private final com.example.roomly.services.userService userService;


    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/signup")
    public String signup(@ModelAttribute("user") @Valid users user) {
        return "signUp";
    }


    @PostMapping("/signup")
    public String signup(@ModelAttribute("user") users user, BindingResult result) {
        if(result.hasErrors()) {
            return "signUp";
        } else {
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            userService.createUser(user);
            return "signUp";
        }
    }

}
