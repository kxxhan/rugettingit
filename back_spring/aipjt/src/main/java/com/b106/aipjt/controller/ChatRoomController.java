package com.b106.aipjt.controller;

import com.b106.aipjt.domain.redishash.ChatRoom;
import com.b106.aipjt.service.ChatRoomRedisService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping(value = "/chat")
@Log4j2
public class ChatRoomController {

    private final ChatRoomRedisService chatRoomRedisService;

    public ChatRoomController(ChatRoomRedisService chatRoomRedisService) {
        this.chatRoomRedisService = chatRoomRedisService;
    }

    // 새 소켓 연결 생성
    @PostMapping(value = "/room")
    public String create() {
        ChatRoom chatRoom = chatRoomRedisService.newChatRoom();
        log.info("# New room");
        return chatRoom.getRoomId();
    }

    // 채팅룸 번호로 연결 찾기
    @GetMapping(value = "/room/{roomId}")
    public ResponseEntity<Optional<ChatRoom>> getRoom(@PathVariable String roomId) {
        log.info("# Get Chat Room, room Id is ", roomId);
        Optional<ChatRoom> chatRoom = chatRoomRedisService.findChatRoomById(roomId);
        if (chatRoom.isPresent()) {
            return new ResponseEntity<>(chatRoom, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(chatRoom, HttpStatus.NO_CONTENT);
        }
    }
}
