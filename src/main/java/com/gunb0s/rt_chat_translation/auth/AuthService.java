package com.gunb0s.rt_chat_translation.auth;

import com.gunb0s.rt_chat_translation.common.GithubAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final GithubAuthService githubAuthService;

    public String oAuth(String code) {
        String accessToken = githubAuthService.requestAccessToken(code);
        return "signup";
    }
}
