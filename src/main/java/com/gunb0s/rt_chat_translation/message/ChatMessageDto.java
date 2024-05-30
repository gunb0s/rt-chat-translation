package com.gunb0s.rt_chat_translation.message;

import com.gunb0s.rt_chat_translation.user.UserDto;
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
