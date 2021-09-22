package com.b106.aipjt.domain.redishash;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@RedisHash("chatRoom")
public class ChatRoom {

    @Id
    private String roomId;
    private Set<WebSocketSession> sessions = new HashSet<>();

    @Builder
    public ChatRoom open() {
        ChatRoom chatroom = new ChatRoom();
        chatroom.roomId = UUID.randomUUID().toString();
        return chatroom;
    }
}
