package com.example.roomly.controllers;


import com.example.roomly.Entities.Enum.roles;
import com.example.roomly.Entities.users;
import com.example.roomly.Entities.wsEntities.chat;
import com.example.roomly.Entities.wsEntities.chatMessage;
import com.example.roomly.repositories.chatMessageRepository;
import com.example.roomly.repositories.chatRepository;
import com.example.roomly.repositories.userRepository;
import com.example.roomly.services.CustomUserDetailsService;
import com.example.roomly.services.chatService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class wsChatController {
    @Autowired private SimpMessagingTemplate messagingTemplate;
    private final CustomUserDetailsService userDetailsService;
    private final chatMessageRepository messageRepository;
    private final userRepository userRepository;
    private final chatService chatService;
    private final chatRepository chatRepository;

    @GetMapping("/chat")
    public String chat(Model model) {
        users currentUser = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());

        boolean hasRoleAdmin = currentUser.getRoles().contains(roles.ADMIN);
        List<chat> chats = currentUser.getChats();
        List<chatMessage> chatMessages = new ArrayList<>();
        model.addAttribute("hasRoleAdmin", hasRoleAdmin);
        model.addAttribute("curr", currentUser);
        model.addAttribute("chats", chats);
        return "chat";
    }

    @PostMapping("/createChat")
    public String createChat(@ModelAttribute("chat") chat chat) {
        chatService.createChat(chat);
        return "chat";
    }


    @MessageMapping("/chat.{chat_id}.sendMessage")
    public chatMessage sendMessage(@Payload chatMessage message, Long chat_id) {
//        chat chat = chatRepository.findChatById(chat_id);
//        chat.getMessages().add(message);
//        chatRepository.save(chat);
//        messageRepository.save(message);

        chatMessage saved = messageRepository.save(message);
        messagingTemplate.convertAndSend("/queue" + chat_id, saved);
        return message;
    }


    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public chatMessage addUser(@Payload chatMessage message, SimpMessageHeaderAccessor headerAccessor){
        headerAccessor.getSessionAttributes().put("username", message.getSender());
        return message;
    }
}
