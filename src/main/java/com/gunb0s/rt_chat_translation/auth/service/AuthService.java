package com.gunb0s.rt_chat_translation.auth.service;

import com.gunb0s.rt_chat_translation.auth.oauth.resource_server.ResourceServer;
import com.gunb0s.rt_chat_translation.auth.oauth.service.OAuthService;
import com.gunb0s.rt_chat_translation.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final OAuthService oAuthService;
    private final UserRepository userRepository;

    public String oAuth(String code, ResourceServer resourceServer) {
        Long userId = oAuthService.requestOAuth(code, resourceServer);
        return userRepository.existsByGithubId(userId) ? login() : signup();
    }

    private String login() {
        return "login";
    }

    private String signup() {
        return "signup";
    }
}
