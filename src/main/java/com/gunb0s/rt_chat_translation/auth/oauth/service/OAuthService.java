package com.gunb0s.rt_chat_translation.auth.oauth.service;

import com.gunb0s.rt_chat_translation.auth.oauth.resource_server.OAuthResourceServer;
import com.gunb0s.rt_chat_translation.auth.oauth.resource_server.OAuthResourceServerFactory;
import com.gunb0s.rt_chat_translation.auth.oauth.resource_server.ResourceServer;
import com.gunb0s.rt_chat_translation.common.service.HttpService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OAuthService {
    private final HttpService httpService;

    public Long requestOAuth(String code, ResourceServer resourceServer) {
        OAuthResourceServer oAuthResourceServer = OAuthResourceServerFactory.getOAuthService(resourceServer, httpService);

        String accessToken = oAuthResourceServer.requestAccessToken(code);
        return oAuthResourceServer.requestUserId(accessToken);
    }
}
