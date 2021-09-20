package com.b106.aipjt.controller;

import com.b106.aipjt.domain.dto.ChatMessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class StompChatController {

    private final SimpMessagingTemplate template;

    @Autowired
    public StompChatController(SimpMessagingTemplate template) {
        this.template = template;
    }

    // prefix로 topic이 설정이 되어있으므로 /app/chat/enter로 퍼블리쉬 요청이 오는 것
    @MessageMapping(value = "/chat/enter")
    public void enterRoom(ChatMessageDto message) {
        message.setMessage(message.getWriter() + " 두둥 등장!");
        template.convertAndSend("/topic/chat/room/" + message.getRoomId(), message);
    }

    @MessageMapping(value = "/chat/message")
    public void message(ChatMessageDto message) {
        template.convertAndSend("/topic/chat/room/" + message.getRoomId(), message);
    }
}
