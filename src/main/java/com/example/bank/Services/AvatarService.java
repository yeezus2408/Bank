package com.example.bank.Services;

import com.example.bank.Repository.AvatarRepository;
import com.example.bank.Repository.userRepository;
import com.example.bank.models.Avatar;
import com.example.bank.models.user;
import com.example.bank.utils.AvatarUtils;
import jakarta.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import static org.springframework.web.util.WebUtils.getRealPath;

@Service
public class AvatarService {
    @Autowired
    private AvatarRepository avatarRepository;
    @Autowired
    private userRepository userRepository;

    @Autowired
    private ResourceLoader resourceLoader;

    String folder_path = "C:/JAVA/bank/src/main/resources/public/avatars/";
    public void uploadAvatar(MultipartFile file) throws IOException {
        String filePath = folder_path + file.getOriginalFilename();
        user user = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        user owner = userRepository.getReferenceById(user.getId());
        Avatar avatar = new Avatar();
        avatarRepository.save(Avatar.builder().name(file.getOriginalFilename()).type(file.getContentType()).data(AvatarUtils.compressImage(file.getBytes())).user(owner).build());

    }

    public void uploadAvatarToFileSystem(MultipartFile file) throws IOException {
        File filePath = new File(folder_path+file.getOriginalFilename());
        user user = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        user owner = userRepository.getReferenceById(user.getId());
        Avatar avatar = avatarRepository.save(Avatar.builder()
                        .name(file.getOriginalFilename())
                        .type(file.getContentType())
                        .user(owner)
                        .local_url(String.valueOf(filePath)).build());
        file.transferTo(filePath);
    }

    public byte[] getAvatar(String fileName) throws IOException {
        Optional<Avatar> dbAvatar = avatarRepository.findByName(fileName);
        byte[] avatars = AvatarUtils.decompressImage(dbAvatar.get().getData());
        return avatars;
    }
}
