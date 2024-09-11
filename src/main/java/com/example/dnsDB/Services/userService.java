package com.example.dnsDB.Services;

import com.example.dnsDB.Repository.userRepository;
import com.example.dnsDB.models.enums.roles;
import com.example.dnsDB.models.user;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class userService {
    public void createUser(user user) {
        String email = user.getEmail();
        if(userRepository.findByEmail(email) != null) {
            throw new RuntimeException("Email already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);
        user.getRoles().add(roles.ROLE_USER);
        log.info("User created");
        userRepository.save(user);
    }

    private final userRepository userRepository;

    private final PasswordEncoder passwordEncoder;

}
