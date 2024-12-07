package com.example.bank.Controller;

import com.example.bank.Repository.AvatarRepository;
import com.example.bank.Repository.userRepository;
import com.example.bank.Services.userService;
import com.example.bank.configs.SecurityConfig;
import com.example.bank.models.Avatar;
import com.example.bank.models.user;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.View;

import java.io.IOException;

@Controller
@Slf4j
@RequiredArgsConstructor
public class userController {


    private final PasswordEncoder passwordEncoder;
    private final userService userService;
    private final userRepository userRepository;
    private final SecurityConfig securityConfig;
    private final AvatarRepository avatarRepository;

    @GetMapping("/login")
    public String login(@ModelAttribute("user") user user, Model model) {
        user currUser = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("curr", currUser);
        return "login";
    }


    @GetMapping("/registration")
    public String registration(@ModelAttribute("user") user user, Model model) {
        user currUser = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        if(currUser != null) {
            
        }
        return "registration";
    }


    @PostMapping("/registration")
    public String createUser(@Valid @ModelAttribute("user") user user, BindingResult result, Model model) throws IOException {
        if (result.hasErrors()) {
            return "registration";
        }
        else if (securityConfig.isValidPassword(user.getPassword())) {
            model.addAttribute("errorPassword", securityConfig.isValidPassword(user.getPassword()));
            return "registration";
        }
        else {
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            System.out.println(encodedPassword + " " + user.getPassword());
            userService.createUser(user);
            return "redirect:/login";
        }
    }

    @GetMapping("/profile")
    public String profile(@ModelAttribute("user") user user, Model model) {
        user = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        Avatar avatar = avatarRepository.findAvatarByUserId(user.getId());
        model.addAttribute("avatarUrl", "/public/avatars/"+avatar.getName());
        model.addAttribute("user", user);
        model.addAttribute("avatar", avatar);
        return "profile";
    }
}
