package com.gunb0s.rt_chat_translation.auth.service;

import com.gunb0s.rt_chat_translation.auth.controller.dto.AuthResponseDto;
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
    public AuthResponseDto oAuth(String code, ResourceServer resourceServer) {
        OAuthUserInfo oAuthUserInfo = oAuthService.requestOAuth(code, resourceServer);
        return userRepository.existsByGithubId(oAuthUserInfo.getId())
                ? login(oAuthUserInfo.getId())
                : signup(oAuthUserInfo.getId(), oAuthUserInfo.getUsername());
    }

    private AuthResponseDto login(Long githubUserId) {
        User user = userRepository.findByGithubId(githubUserId).orElseThrow(() -> new IllegalArgumentException("가입되지 않은 사용자입니다."));
        String accessToken = jwtUtil.generateToken(user.getGithubId(), user.getUsername());
        return AuthResponseDto.builder()
                .accessToken(accessToken)
                .userId(user.getId())
                .username(user.getUsername())
                .build();
    }

    private AuthResponseDto signup(Long githubUserId, String username) {
        userRepository.findByGithubId(githubUserId).ifPresent(user -> {
            throw new IllegalArgumentException("이미 가입된 사용자입니다.");
        });

        User user = User.builder()
                .githubId(githubUserId)
                .username(username)
                .build();
        userRepository.save(user);

        String accessToken = jwtUtil.generateToken(githubUserId, username);
        return AuthResponseDto.builder()
                .accessToken(accessToken)
                .userId(user.getId())
                .username(user.getUsername())
                .build();
    }
}
