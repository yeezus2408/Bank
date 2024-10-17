package com.example.bank.Controller;

import com.example.bank.Repository.userRepository;
import com.example.bank.Services.AvatarService;
import com.example.bank.models.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping
public class AvatarController {
    private AvatarService avatarService;
    private userRepository userRepository;


    @GetMapping("/uploadAvatar")
    public String uploadAvatar(Model model) {
        user user = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("user", user);
        return "uploadAvatar";
    }


    @PostMapping("/uploadAvatar")
    public void uploadAvatar(@RequestParam("userId") Long userId, @RequestParam("file")MultipartFile file, Model model) throws IOException {
        avatarService.uploadAvatar(userId,file);
    }

}



