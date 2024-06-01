package com.gunb0s.rt_chat_translation.chatRoom.controller;

import com.gunb0s.rt_chat_translation.auth.service.MyUserDetail;
import com.gunb0s.rt_chat_translation.chatRoom.entity.ChatRoom;
import com.gunb0s.rt_chat_translation.chatRoom.service.ChatRoomService;
import com.gunb0s.rt_chat_translation.common.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat-room")
@RequiredArgsConstructor
public class ChatRoomController {
    private final ChatRoomService chatRoomService;

    @PostMapping
    public ResponseEntity<?> createChatRoom(Authentication authentication) {
        MyUserDetail myUserDetail = (MyUserDetail) authentication.getPrincipal();
        ChatRoom chatRoom = chatRoomService.createChatRoom(myUserDetail.getId());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        ResponseDto.builder()
                                .status(HttpStatus.CREATED.value())
                                .data(chatRoom.getId())
                                .build()
                );
    }

    @GetMapping("/my")
    public ResponseEntity<?> getMyChatRooms() {
        return ResponseEntity
                .ok(
                        ResponseDto.builder()
                            .status(HttpStatus.OK.value())
                            .build()
                );
    }

    @GetMapping("/recent")
    public ResponseEntity<?> getRecentChatRooms() {
        return ResponseEntity
                .ok(
                        ResponseDto.builder()
                            .status(HttpStatus.OK.value())
                            .build()
                );
    }
}
