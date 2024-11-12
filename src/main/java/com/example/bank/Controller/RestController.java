package com.example.bank.Controller;

import com.example.bank.Repository.AvatarRepository;
import com.example.bank.Repository.userRepository;
import com.example.bank.Services.userService;
import com.example.bank.models.Avatar;
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
    private final userRepository userRepository;
    private final AvatarRepository avatarRepository;

//    @GetMapping("/")
//    public void index(Model model, user user) {
////        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
////        boolean isAuthenticated = authentication != null && authentication.isAuthenticated();
//        model.addAttribute("content", "home :: content");
//    }
}
