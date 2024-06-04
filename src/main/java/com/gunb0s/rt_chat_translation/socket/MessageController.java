package com.gunb0s.rt_chat_translation.socket;

import com.gunb0s.rt_chat_translation.chatMessage.controller.dto.ChatMessageDto;
import com.gunb0s.rt_chat_translation.socket.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;

    @MessageMapping("/{chatId}")
    @SendTo("/sub/channel/{chatId}")
    public ChatMessageDto message(@DestinationVariable String chatId, ChatMessageDto chatMessageDto) {
        messageService.saveChatMessage(chatId, chatMessageDto);
        return chatMessageDto;
    }
}
