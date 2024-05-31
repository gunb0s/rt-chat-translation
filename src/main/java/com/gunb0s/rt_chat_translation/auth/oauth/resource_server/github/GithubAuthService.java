package com.gunb0s.rt_chat_translation.auth.oauth.resource_server.github;

import com.gunb0s.rt_chat_translation.auth.oauth.resource_server.OAuthResourceServer;
import com.gunb0s.rt_chat_translation.common.SpringContext;
import com.gunb0s.rt_chat_translation.common.service.HttpService;
import org.springframework.core.env.Environment;
import org.springframework.web.util.UriComponentsBuilder;

public class GithubAuthService implements OAuthResourceServer {
    private final HttpService httpService;
    private final String clientId;
    private final String clientSecret;

    public GithubAuthService(HttpService httpService) {
        this.httpService = httpService;

        Environment environment = SpringContext.getContext().getEnvironment();
        this.clientId = environment.getProperty("github.client.id");
        this.clientSecret = environment.getProperty("github.client.secret");
    }

    @Override
    public String requestAccessToken(String code) {
        String url = UriComponentsBuilder.fromHttpUrl("https://github.com/login/oauth/access_token")
                .queryParam("client_id", clientId)
                .queryParam("client_secret", clientSecret)
                .queryParam("code", code)
                .build()
                .toUriString();
        GithubAccessTokenResponse githubAuthResponse = httpService.post(url, null, GithubAccessTokenResponse.class);
        return githubAuthResponse.getAccess_token();
    }

    public Long requestUserId(String accessToken) {
        String requestUserUrl = UriComponentsBuilder.fromHttpUrl("https://api.github.com/user")
                .build()
                .toUriString();
        GithubUserResponse githubUserResponse = httpService.get(requestUserUrl, accessToken, GithubUserResponse.class);
        return githubUserResponse.getId();
    }
}
