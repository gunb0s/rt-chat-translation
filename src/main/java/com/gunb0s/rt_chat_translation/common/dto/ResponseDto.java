package com.gunb0s.rt_chat_translation.common.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponseDto<T> {
    private final int status;
    private final String message;
    private final T data;

    @Builder
    public ResponseDto(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
