package com.gunb0s.rt_chat_translation.auth.controller.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AuthResponseDto {
    private String accessToken;
    private String userId;
    private String username;

    @Builder
    public AuthResponseDto(String accessToken, String userId, String username) {
        this.accessToken = accessToken;
        this.userId = userId;
        this.username = username;
    }
}
