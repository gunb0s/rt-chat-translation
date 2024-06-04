package com.gunb0s.rt_chat_translation.chatMessage.controller.dto;

import com.gunb0s.rt_chat_translation.chatMessage.entity.ChatMessage;
import com.gunb0s.rt_chat_translation.user.controller.dto.UserDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatMessageDto {
    private Long id;
    private UserDto sender;
    private String payload;
    private LocalDateTime createdAt;

    public ChatMessageDto(ChatMessage chatMessage) {
        this.id = chatMessage.getId();
        this.sender = UserDto.builder()
                .id(chatMessage.getUser().getId())
                .username(chatMessage.getUser().getUsername())
                .build();
        this.payload = chatMessage.getPayload();
    }

    @Builder
    public ChatMessageDto(UserDto sender, String payload) {
        this.sender = sender;
        this.payload = payload;
    }
}
