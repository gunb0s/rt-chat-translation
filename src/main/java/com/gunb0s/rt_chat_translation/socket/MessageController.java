package com.gunb0s.rt_chat_translation.socket;

import com.gunb0s.rt_chat_translation.chatMessage.controller.dto.ChatMessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class MessageController {
    @MessageMapping("/{chatId}")
    @SendTo("/sub/channel/{chatId}")
    public ChatMessageDto message(@DestinationVariable String chatId, ChatMessageDto chatMessageDto) {
        return new ChatMessageDto(chatMessageDto.getSender(), chatMessageDto.getPayload());
    }
}
