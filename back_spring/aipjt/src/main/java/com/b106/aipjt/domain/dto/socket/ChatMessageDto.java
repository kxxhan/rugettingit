package com.b106.aipjt.domain.dto.socket;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessageDto {

    private String roomId;
    private MessageTypeCode code = MessageTypeCode.CHAT;
    private String writer;
    private String message;
}
