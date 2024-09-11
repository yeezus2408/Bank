package com.example.dnsDB.Controller;

import com.example.dnsDB.Services.userService;
import com.example.dnsDB.models.user;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class userController {
    private final userService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }


    @PostMapping("/registration")
    public void createUser(user user) {
        userService.createUser(user);
    }
}
