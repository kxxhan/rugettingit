package com.b106.aipjt.domain.dto.room;

import com.b106.aipjt.domain.redishash.Room;
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
    @Builder.Default
    private boolean isStart = false;
    @Builder.Default
    private int currentRound = 0;
    @Builder.Default
    private int maxRound = 3;
    @Builder.Default
    private int roundTime = 60;
    @Builder.Default
    private List<RoomUserResponseDto> userList = new ArrayList<>();



    public static RoomResponseDto toRoom(Room room, List<RoomUserResponseDto> roomUsers) {
        return RoomResponseDto.builder().id(room.getId())
            .currentRound(room.getCurrentRound())
            .maxRound(room.getMaxRound())
            .roundTime(room.getRoundTime())
            .userList(roomUsers)
            .build();
    }

}
