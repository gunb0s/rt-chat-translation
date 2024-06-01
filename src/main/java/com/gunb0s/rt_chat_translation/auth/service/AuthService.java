package com.gunb0s.rt_chat_translation.auth.service;

import com.gunb0s.rt_chat_translation.auth.jwt.JwtUtil;
import com.gunb0s.rt_chat_translation.auth.oauth.resource_server.OAuthUserInfo;
import com.gunb0s.rt_chat_translation.auth.oauth.resource_server.ResourceServer;
import com.gunb0s.rt_chat_translation.auth.oauth.service.OAuthService;
import com.gunb0s.rt_chat_translation.user.entity.User;
import com.gunb0s.rt_chat_translation.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {
    private final OAuthService oAuthService;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    @Transactional
    public String oAuth(String code, ResourceServer resourceServer) {
        OAuthUserInfo oAuthUserInfo = oAuthService.requestOAuth(code, resourceServer);
        return userRepository.existsByGithubId(oAuthUserInfo.getId()) ? login() : signup(oAuthUserInfo.getId(), oAuthUserInfo.getUsername());
    }

    private String login() {
        return "login";
    }

    private String signup(Long userId, String username) {
        User user = User.builder()
                .githubId(userId)
                .username(username)
                .build();
        userRepository.save(user);

        return jwtUtil.generateToken(userId, username);
    }
}
