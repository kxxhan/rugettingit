package com.b106.aipjt.domain.dto.room;

import com.b106.aipjt.domain.redishash.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class RoomResponseDto {
    private String id;
    private boolean isStart = false;
    private int round = 0;
    private int maxRound = 3;
    private int roundTime = 60;
    private List<RoomUserResponseDto> userList = new ArrayList();

}
