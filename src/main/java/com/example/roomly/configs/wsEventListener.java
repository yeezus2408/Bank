package com.example.roomly.configs;

import com.example.roomly.Entities.Enum.messageType;
import com.example.roomly.Entities.wsEntities.chatMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@RequiredArgsConstructor
@Slf4j
public class wsEventListener {
    private final SimpMessageSendingOperations messagingTemplate;
    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = (String) accessor.getSessionAttributes().get("username");
        if (username != null) {
            log.info("Disconnected from " + username);
            var ChatMessage = chatMessage.builder()
                    .type(messageType.LEAVE)
                    .Sender(username)
                    .build();
            messagingTemplate.convertAndSend("/topic/public", ChatMessage);
        }
    }
}
