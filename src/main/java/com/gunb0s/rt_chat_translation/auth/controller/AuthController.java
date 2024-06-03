package com.gunb0s.rt_chat_translation.auth.controller;

import com.gunb0s.rt_chat_translation.auth.controller.dto.AuthResponseDto;
import com.gunb0s.rt_chat_translation.auth.oauth.resource_server.ResourceServer;
import com.gunb0s.rt_chat_translation.auth.service.AuthService;
import com.gunb0s.rt_chat_translation.common.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping()
    public ResponseEntity<?> oAuth(@RequestParam String code, @RequestParam("resource_server") ResourceServer resourceServer) {
        AuthResponseDto responseDto = authService.oAuth(code, resourceServer);
        return ResponseEntity
                .ok(
                        ResponseDto.builder()
                            .status(HttpStatus.CREATED.value())
                            .data(responseDto)
                            .build()
                );
    }
}
