package com.gunb0s.rt_chat_translation.user.controller.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserDto {
    private String id;
    private String username;

    @Builder
    public UserDto(String id, String username) {
        this.id = id;
        this.username = username;
    }
}
