package com.example.bank.Controller;

import com.example.bank.Repository.AvatarRepository;
import com.example.bank.Repository.userRepository;
import com.example.bank.Services.AvatarService;
import com.example.bank.models.Avatar;
import com.example.bank.models.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping
public class AvatarController {
    @Autowired
    private AvatarService avatarService;
    @Autowired
    private userRepository userRepository;
    @Autowired
    private AvatarRepository avatarRepository;


    @PostMapping("/uploadAvatar")
    public void uploadAvatarToFileSystem(@RequestParam("avatar") MultipartFile avatar) throws IOException {
        user user = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        Avatar avatarka = avatarRepository.findAvatarByUserId(user.getId());
        if(avatarka != null) {
            avatarRepository.delete(avatarka);
        }
        avatarService.uploadAvatarToFileSystem(avatar);
    }


    @GetMapping("/downloadAvatar")
    public ResponseEntity<?> downloadAvatar(@PathVariable String fileName) throws IOException {
        byte[] avatarData = avatarService.getAvatar(fileName);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(avatarData);
    }

}



