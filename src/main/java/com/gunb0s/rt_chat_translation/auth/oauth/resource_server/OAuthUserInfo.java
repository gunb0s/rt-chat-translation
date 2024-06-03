package com.gunb0s.rt_chat_translation.auth.oauth.resource_server;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OAuthUserInfo {
    private final Long id;
    private final String username;

    @Builder
    public OAuthUserInfo(Long id, String username) {
        this.id = id;
        this.username = username;
    }
}
