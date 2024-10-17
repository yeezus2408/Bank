package com.example.bank.Services;

import com.example.bank.Repository.AvatarRepository;
import com.example.bank.Repository.userRepository;
import com.example.bank.models.Avatar;
import com.example.bank.models.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class AvatarService {
    @Autowired
    private AvatarRepository avatarRepository;
    private userRepository userRepository;

    public void uploadAvatar(Long userId, MultipartFile file) throws IOException {
        user user = userRepository.findById(userId).orElse(null);
        Avatar avatar = new Avatar();
        avatar.setUser(user);
        avatar.setData(file.getBytes());
        avatarRepository.save(avatar);
    }
}
