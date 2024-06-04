package com.gunb0s.rt_chat_translation.socket;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MessageController {
    @MessageMapping("/{chatId}")
    @SendTo("/sub/channel/{chatId}")
    public ChatMessage message(@DestinationVariable String chatId, ChatMessage chatMessageDto) {
        log.info("chatId: {}", chatId);
        return new ChatMessage(chatMessageDto.sender(), chatMessageDto.payload());
    }
}
