package com.gunb0s.rt_chat_translation.chatMessage.controller.dto;

import com.gunb0s.rt_chat_translation.user.controller.dto.UserDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ChatMessageDto {
    private UserDto sender;
    private String content;
}
