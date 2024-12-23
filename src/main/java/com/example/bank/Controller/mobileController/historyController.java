package com.example.bank.Controller.mobileController;

import com.example.bank.Repository.operationsRepository;
import com.example.bank.Repository.userRepository;
import com.example.bank.models.operations;
import com.example.bank.models.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class historyController {
    @Autowired
    userRepository userRepository;
    @Autowired
    operationsRepository operationRepository;

    @GetMapping("/getOperations")
    public List<operations> getOperations() {
        user currentUser = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        return operationRepository.findAllByWho(currentUser.getId());
    }
}
