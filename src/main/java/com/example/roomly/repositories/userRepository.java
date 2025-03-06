package com.example.roomly.repositories;

import com.example.roomly.Entities.users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface userRepository extends JpaRepository<users, Long> {
    users findByEmail(String email);
    users findUsersById(Long id);
}
