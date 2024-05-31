package com.gunb0s.rt_chat_translation.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("auth/{code}")
    public String signup(@PathVariable String code) {
        return authService.signup(code);
    }
}
