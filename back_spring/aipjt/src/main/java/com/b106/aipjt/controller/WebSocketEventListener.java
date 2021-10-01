package com.b106.aipjt.controller;

import com.b106.aipjt.domain.dto.socket.ChatMessageDto;
import com.b106.aipjt.domain.dto.socket.MessageTypeCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class WebSocketEventListener {

    private  static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);

    private final SimpMessageSendingOperations messageSendingOperations;

    public WebSocketEventListener(SimpMessageSendingOperations messageSendingOperations) {
        this.messageSendingOperations = messageSendingOperations;
    }

    @EventListener
    public void onConnectEvent(SessionConnectedEvent connectedEvent) {
        logger.info("Created New WebSocket Connection!");
    }

    @EventListener
    public void onDisconnectEvent(SessionDisconnectEvent disconnectEvent){
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(disconnectEvent.getMessage());
        String username = (String) headerAccessor.getSessionAttributes().get("username");
        System.out.println("퇴장한사람" + username);
        if (username != null) {
            logger.info("User disconnected: " + username);
            ChatMessageDto messageDto = new ChatMessageDto();
            messageDto.setCode(MessageTypeCode.LEAVE);
            messageDto.setWriter(username);
            messageDto.setMessage(" 나갔다!");

            messageSendingOperations.convertAndSend("/sub/chat/room/" + messageDto.getRoomId(), messageDto);
        }

    }
}
