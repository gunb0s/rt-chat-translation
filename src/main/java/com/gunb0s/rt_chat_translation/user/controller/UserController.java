package com.gunb0s.rt_chat_translation.user.controller;

import com.gunb0s.rt_chat_translation.auth.service.MyUserDetail;
import com.gunb0s.rt_chat_translation.common.dto.ResponseDto;
import com.gunb0s.rt_chat_translation.user.controller.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @GetMapping("/me")
    public ResponseEntity<?> me(Authentication authentication) {
        MyUserDetail myUserDetail = (MyUserDetail) authentication.getPrincipal();
        UserDto userDto = UserDto.builder()
                .id(myUserDetail.getId())
                .username(myUserDetail.getUsername())
                .build();

        return ResponseEntity
                .ok(ResponseDto.builder()
                        .status(HttpStatus.OK.value())
                        .data(userDto)
                        .build()
                );
    }
}
