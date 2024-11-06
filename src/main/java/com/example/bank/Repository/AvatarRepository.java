package com.example.bank.Repository;
import com.example.bank.models.Avatar;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AvatarRepository extends JpaRepository<Avatar, Long> {
    @Transactional
    Avatar findAvatarByUserId(Long userId);
    Optional<Avatar> findByName(String fileName);
}
