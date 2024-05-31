package com.gunb0s.rt_chat_translation.auth;

import com.gunb0s.rt_chat_translation.common.HttpService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final HttpService httpService;
    public String signup(String code) {
        return httpService.post(code);
    }
}
