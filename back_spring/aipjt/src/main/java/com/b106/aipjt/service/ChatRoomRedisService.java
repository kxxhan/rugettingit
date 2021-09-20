package com.b106.aipjt.service;

import com.b106.aipjt.domain.redishash.ChatRoom;
import com.b106.aipjt.domain.repository.ChatRoomRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChatRoomRedisService {
    private final ChatRoomRepository chatRoomRepository;

    public ChatRoomRedisService(ChatRoomRepository chatRoomRepository) {
        this.chatRoomRepository = chatRoomRepository;
    }

    public ChatRoom newChatRoom() {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.open();
        chatRoomRepository.save(chatRoom);
        return chatRoom;
    }

    public Optional<ChatRoom> findChatRoomById(String roomId) {
        Optional<ChatRoom> chatRoom = chatRoomRepository.findById(roomId);
        return chatRoom;
    }
}
