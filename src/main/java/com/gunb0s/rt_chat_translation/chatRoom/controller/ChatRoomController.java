package com.gunb0s.rt_chat_translation.chatRoom.controller;

import com.gunb0s.rt_chat_translation.auth.service.MyUserDetail;
import com.gunb0s.rt_chat_translation.chatRoom.controller.dto.ChatRoomDto;
import com.gunb0s.rt_chat_translation.chatRoom.entity.ChatRoom;
import com.gunb0s.rt_chat_translation.chatRoom.service.ChatRoomService;
import com.gunb0s.rt_chat_translation.common.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("{chatRoomId}")
    public ResponseEntity<?> getChatRoom(@PathVariable String chatRoomId) {
        ChatRoom chatRoom = chatRoomService.getChatRoomById(chatRoomId);
        ChatRoomDto chatRoomDto = new ChatRoomDto(chatRoom);

        return ResponseEntity
                .ok(
                        ResponseDto.builder()
                                .status(HttpStatus.OK.value())
                                .data(chatRoomDto)
                                .build()
                );
    }

    @GetMapping("/my")
    public ResponseEntity<?> getMyChatRooms(Authentication authentication) {
        MyUserDetail myUserDetail = (MyUserDetail) authentication.getPrincipal();
        List<ChatRoom> myChatRooms = chatRoomService.getMyChatRooms(myUserDetail.getId());
        List<ChatRoomDto> list = myChatRooms
                .stream()
                .map(ChatRoomDto::new)
                .toList();

        return ResponseEntity
                .ok(
                        ResponseDto.builder()
                            .status(HttpStatus.OK.value())
                            .data(list)
                            .build()
                );
    }

    @GetMapping("/recent")
    public ResponseEntity<?> getRecentChatRooms() {
        List<ChatRoom> recentChatRooms = chatRoomService.getRecentChatRooms();
        List<ChatRoomDto> list = recentChatRooms
                .stream()
                .map(ChatRoomDto::new)
                .toList();

        return ResponseEntity
                .ok(
                        ResponseDto.builder()
                                .status(HttpStatus.OK.value())
                                .data(list)
                                .build()
                );
    }
}
