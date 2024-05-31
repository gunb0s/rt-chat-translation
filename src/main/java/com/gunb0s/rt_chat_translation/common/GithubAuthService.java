package com.gunb0s.rt_chat_translation.common;

import com.gunb0s.rt_chat_translation.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor
public class GithubAuthService {
    private final HttpService httpService;
    private final UserRepository userRepository;

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    public String requestAccessToken(String code) {
        String url = UriComponentsBuilder.fromHttpUrl("https://github.com/login/oauth/access_token")
                .queryParam("client_id", clientId)
                .queryParam("client_secret", clientSecret)
                .queryParam("code", code)
                .build()
                .toUriString();
        GithubAccessTokenResponse githubAuthResponse = httpService.post(url, null, GithubAccessTokenResponse.class);

        String requestUserUrl = UriComponentsBuilder.fromHttpUrl("https://api.github.com/user")
                .build()
                .toUriString();
        GithubUserResponse githubUserResponse = httpService.get(requestUserUrl, githubAuthResponse.getAccess_token(), GithubUserResponse.class);

        return "hi";
    }
}
