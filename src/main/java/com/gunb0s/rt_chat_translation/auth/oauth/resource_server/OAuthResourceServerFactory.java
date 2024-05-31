package com.gunb0s.rt_chat_translation.auth.oauth.resource_server;

import com.gunb0s.rt_chat_translation.auth.oauth.resource_server.github.GithubAuthService;
import com.gunb0s.rt_chat_translation.common.service.HttpService;

public class OAuthResourceServerFactory {
    static public OAuthResourceServer getOAuthService(ResourceServer resourceServer, HttpService httpService) {
        switch (resourceServer) {
            case GITHUB:
                return new GithubAuthService(httpService);
            default:
                throw new IllegalArgumentException("Invalid ResourceServer");
        }
    }
}
