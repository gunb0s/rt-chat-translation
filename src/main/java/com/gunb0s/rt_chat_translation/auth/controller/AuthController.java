package com.gunb0s.rt_chat_translation.auth.controller;

import com.gunb0s.rt_chat_translation.auth.oauth.resource_server.ResourceServer;
import com.gunb0s.rt_chat_translation.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/auth")
    public ResponseEntity<String> oAuth(@RequestParam String code, @RequestParam("resource_server") ResourceServer resourceServer) {
        return ResponseEntity.ok().body(
                authService.oAuth(code, resourceServer)
        );
    }
}
