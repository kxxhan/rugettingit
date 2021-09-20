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

    // prefix로 topic이 설정이 되어있으므로 /pub/chat/enter로 퍼블리쉬 요청이 오는 것
    @MessageMapping(value = "/chat/enter")
    public void enterRoom(ChatMessageDto chatMessageDto) {
        chatMessageDto.setMessage(chatMessageDto.getWriter() + " 두둥 등장!");
        // /topic/chat/room/{roomId}를 구독하는 애들한테 메시지를 퍼블리쉬
        template.convertAndSend("/topic/chat/room/" + chatMessageDto.getRoomId(), chatMessageDto);
    }

    @MessageMapping(value = "/chat/message")
    public void message(ChatMessageDto chatMessageDto) {
        // /topic/chat/room/{roomId}를 구독하는 애들한테 메시지를 퍼블리쉬
        template.convertAndSend("/topic/chat/room/" + chatMessageDto.getRoomId(), chatMessageDto);
    }
}
