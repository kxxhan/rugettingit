package com.b106.aipjt.domain.dto.room;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RoomUserResponseDto {
    int score;
    String avatar;
    String nickname;
    boolean isSuper;

}
