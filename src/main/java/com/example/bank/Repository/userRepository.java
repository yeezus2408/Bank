package com.example.bank.Repository;

import com.example.bank.models.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface userRepository extends JpaRepository<user, Long> {
    user findByEmail(String email);
    user findByUsername(String username);
    user findById(long id);
}
