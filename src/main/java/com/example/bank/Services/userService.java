package com.example.bank.Services;

import com.example.bank.Repository.userRepository;
import com.example.bank.models.enums.roles;
import com.example.bank.models.user;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.example.bank.models.Avatar;

import java.io.IOException;
import java.security.Principal;

@Service
@Slf4j
@RequiredArgsConstructor
public class userService {
    private final userRepository userRepository;



    public void createUser(user user) throws IOException {
        String email = user.getEmail();
        user.setActive(true);
        user.getRoles().add(roles.ROLE_USER);
        log.info("User created");
        userRepository.save(user);
    }







    public user findUserByPrincipal(Principal principal){
        if(principal == null) return null;
        return userRepository.findByEmail(principal.getName());
    }

    public boolean hasAuhorize(){
        user user = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        if(user == null){
            return false;
        } else {return true;}
    }


}
