package com.example.roomly.controllers;

import com.example.roomly.Entities.Enum.roles;
import com.example.roomly.services.userService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RESTController {
    private final userService userService;
    @PostMapping("/setRole/{id}/{role}")
    public void setRole(@PathVariable Long id, @PathVariable roles role) {
        userService.setRole(id, role);
    }
}
