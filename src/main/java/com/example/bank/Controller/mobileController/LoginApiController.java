package com.example.bank.Controller.mobileController;

import com.example.bank.Repository.userRepository;
import com.example.bank.models.DTO.UserDTO;
import com.example.bank.models.user;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LoginApiController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    userRepository userRepo;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserDTO login) throws JsonProcessingException {
        user currUser = userRepo.findByUsername(login.getUsername());
        if(login.getUsername().isEmpty()) {
            return new ResponseEntity("Username does not exist", HttpStatus.NOT_FOUND);
        }
        boolean hashedPassword = passwordEncoder.matches(login.getPassword(), currUser.getPassword());

        if(!hashedPassword) {
            return new ResponseEntity("Wrong password", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(currUser, HttpStatus.OK);
    }
}
