package com.gunb0s.rt_chat_translation.auth.oauth.resource_server;

public interface OAuthResourceServer {
    String requestAccessToken(String code);

    Long requestUserId(String accessToken);
}
