package com.example.roomly.services;

import com.example.roomly.Entities.users;
import com.example.roomly.Entities.wsEntities.chat;
import com.example.roomly.repositories.chatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class chatService {
    private final CustomUserDetailsService customUserDetailsService;
    private final chatRepository chatRepository;

    public void createChat(chat chat) {
        users currUser = customUserDetailsService.getCurrentUser();
        List<users> chatsUsers = chat.getParticipants();
        chat.setUpdated(LocalTime.now());
        String chatName = "";
        for (users chatsUser : chatsUsers) {
            chatName = ", " + chatsUser.getFirstname();
        }
        chat.getParticipants().add(currUser);
        chat.setName(chatName);
        chatRepository.save(chat);
    }
}
