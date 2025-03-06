package com.example.roomly.repositories;

import com.example.roomly.Entities.wsEntities.chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface chatRepository extends JpaRepository<chat, Long> {
    chat findChatById(Long id);
}
