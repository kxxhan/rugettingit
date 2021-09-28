package com.b106.aipjt.controller;

import com.b106.aipjt.domain.dto.MessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class SocketController {

    private final SimpMessagingTemplate template;

    @Autowired
    public SocketController(SimpMessagingTemplate template) {
        this.template = template;
    }

    // prefix로 pub 설정이 되어있으므로 /pub/chat/enter로 퍼블리쉬 요청이 오는 것
    @MessageMapping(value = "/chat/enter")
    public void enterRoom(MessageDto messageDto) {
        System.out.println(messageDto.getRoomId());
        messageDto.setMessage("두둥 등장!");
        // /pub/chat/room/{roomId}를 구독하는 애들한테 메시지를 퍼블리쉬
        template.convertAndSend("/sub/chat/room/" + messageDto.getRoomId(), messageDto);
    }

    @MessageMapping(value = "/chat/message")
    public void message(MessageDto messageDto) {
        // /pub/chat/room/{roomId}를 구독하는 애들한테 메시지를 퍼블리쉬
        template.convertAndSend("/sub/chat/room/" + messageDto.getRoomId(), messageDto);
    }
}
