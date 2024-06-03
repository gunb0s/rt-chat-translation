package com.gunb0s.rt_chat_translation.chatRoom.controller.dto;

import com.gunb0s.rt_chat_translation.chatRoom.entity.ChatRoom;
import com.gunb0s.rt_chat_translation.user.controller.dto.UserDto;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ChatRoomDto {
    private String id;
    private String name;
    private UserDto[] users;

    public ChatRoomDto(ChatRoom chatRoom) {
        this.id = chatRoom.getId();
        this.name = chatRoom.getName();
        this.users = chatRoom.getChatRoomUsers().stream()
                .map(chatRoomUser -> new UserDto(chatRoomUser.getUser().getId(), chatRoomUser.getUser().getUsername()))
                .toArray(UserDto[]::new);
    }

    @Builder
    public ChatRoomDto(String id, String name, UserDto[] users) {
        this.id = id;
        this.name = name;
        this.users = users;
    }
}
