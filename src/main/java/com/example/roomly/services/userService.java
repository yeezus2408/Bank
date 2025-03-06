package com.example.roomly.services;

import com.example.roomly.Entities.Enum.roles;
import com.example.roomly.Entities.users;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class userService {
    private final com.example.roomly.repositories.userRepository userRepository;

    public void createUser(users user) {
        user.getRoles().add(roles.USER);
        user.setAge(LocalDate.now().getYear() - user.getDateOfBirth().getYear());
        userRepository.save(user);
        log.info("User created");
    }

    public void setRole(Long userId, roles role) {
        users user = userRepository.findUsersById(userId);
        user.getRoles().add(role);
        userRepository.save(user);
    }
}
