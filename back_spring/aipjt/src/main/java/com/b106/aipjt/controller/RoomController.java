package com.b106.aipjt.controller;


import com.b106.aipjt.domain.dto.ResponseDto;
import com.b106.aipjt.domain.dto.room.RoomCreateRequestDto;
import com.b106.aipjt.domain.dto.room.RoomResponseDto;
import com.b106.aipjt.domain.dto.room.RoomUserResponseDto;
import com.b106.aipjt.domain.redishash.Room;
import com.b106.aipjt.domain.redishash.User;
import com.b106.aipjt.service.RoomRedisService;
import com.b106.aipjt.service.UserRedisService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/room")
public class RoomController {
    private final UserRedisService userRedisService;
    private final RoomRedisService roomRedisService;

    // 방 조회
    @GetMapping("")
    public ResponseDto<RoomResponseDto> getRoom(@RequestHeader(value="User-Id") String userId,
                                                @RequestParam String roomId){
        // 0. Room 조회
        Room room = roomRedisService.findOne(roomId);

        // 1.  User 리스트를 RoomUserResponseDto 변환
        List<RoomUserResponseDto> roomUsers = new ArrayList<>();
        room.getUserList().forEach(u -> {
            roomUsers.add(new RoomUserResponseDto(0, u.getAvatar(), u.getNickname(), room.getSuperUser().getId().equals(u.getId())));
        });

        // 2. Room 객체를 RoomResponseDto 변환
        RoomResponseDto build = RoomResponseDto.builder()
            .id(room.getId())
            .round(room.getRound())
            .maxRound(room.getMaxRound())
            .roundTime(room.getRoundTime())
            .userList(roomUsers)
            .build();

        // 3. RoomResponseDto를 리턴
        return new ResponseDto(HttpStatus.OK.value(), "방 조회 성공", build);
    }

    // 방 생성
    @PostMapping("")
    public ResponseDto<RoomResponseDto> createRoom(@RequestHeader(value="User-Id") String userId,
                                                   @Valid @RequestBody RoomCreateRequestDto roomCreateDto){
        Room result = roomRedisService.createRoom(userId, roomCreateDto.getAvatar(), roomCreateDto.getNickname());
        return new ResponseDto(HttpStatus.CREATED.value(), "방 생성 성공", RoomResponseDto.builder().id(result.getId()).build());
    }


    // 유저의 방 입장
    @PostMapping("/user")
    public ResponseDto<RoomResponseDto> joinRoom(@RequestHeader(value="User-Id") String userId,
                                                 @RequestParam String roomId) {
        Room room = roomRedisService.joinRoom(userId, roomId);

        // 1.  User 리스트를 RoomUserResponseDto 변환
        List<RoomUserResponseDto> roomUsers = new ArrayList<>();
        room.getUserList().forEach(u -> {
            roomUsers.add(new RoomUserResponseDto(0, u.getAvatar(), u.getNickname(), room.getSuperUser().getId().equals(u.getId())));
        });

        // 2. Room 객체를 RoomResponseDto 변환
        RoomResponseDto build = RoomResponseDto.builder()
            .id(room.getId())
            .round(room.getRound())
            .maxRound(room.getMaxRound())
            .roundTime(room.getRoundTime())
            .userList(roomUsers)
            .build();

        // 3. RoomResponseDto를 리턴
        return new ResponseDto(HttpStatus.OK.value(), "방 입장 성공", build);
    }

    // 유저의 방 퇴장
    @DeleteMapping("/user")
    public void leaveRoom(@RequestHeader(value="User-Id") String userId,
                                                 @RequestParam String roomId) {
        Room room = roomRedisService.leaveRoom(userId, roomId);
    }

}
