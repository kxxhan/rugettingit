package com.b106.aipjt.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageDto {

    private String roomId;
    private String messageTypeCode;
    private String writer;
    private String message;

    @AllArgsConstructor
    private enum MessageTypeCode {
        CHAT,
        ROOM_INFO,
        DRAWING;
    }
}
