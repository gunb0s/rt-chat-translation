package com.gunb0s.rt_chat_translation.auth.oauth.resource_server.github;

import lombok.Getter;

@Getter
public class GithubAccessTokenResponse {
    private String access_token;
    private String token_type;
    private String scope;
}
