package com.example.dnsDB.Repository;

import com.example.dnsDB.models.user;
import org.springframework.data.jpa.repository.JpaRepository;

public interface userRepository extends JpaRepository<user, Long> {
    user findByEmail(String email);
}
