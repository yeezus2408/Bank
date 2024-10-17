package com.example.bank.Controller;

import com.example.bank.Services.userService;
import com.example.bank.models.user;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class RestController {
    private final userService userService;

    @GetMapping("/")
    public String index(Model model, user user) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        boolean isAuthenticated = authentication != null && authentication.isAuthenticated();
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("content", "home :: content");
        return "index";
    }
}
