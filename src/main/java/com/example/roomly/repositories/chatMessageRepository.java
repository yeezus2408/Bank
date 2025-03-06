package com.example.roomly.repositories;

import com.example.roomly.Entities.wsEntities.chatMessage;
import org.springframework.data.repository.CrudRepository;

public interface chatMessageRepository extends CrudRepository<chatMessage, Long> {
}
