package com.b106.aipjt.domain.repository;

import com.b106.aipjt.domain.redishash.ChatRoom;
import org.springframework.data.repository.CrudRepository;

public interface ChatRoomRepository extends CrudRepository<ChatRoom, String> {
}
