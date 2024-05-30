package com.gunb0s.rt_chat_translation.socket;

import com.gunb0s.rt_chat_translation.message.ChatMessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class MessageController {

    /**
     * If a message is sent to /{chatId} destination, the message() method is called.
     * The return value is broadcast to all subscribers of /sub/channel/{chatId}, as specified in the @SendTo annotation.
     */
    @MessageMapping("/{chatId}")
    @SendTo("/sub/channel/{chatId}")
    public ChatMessageDto message(@DestinationVariable String chatId, ChatMessageDto chatMessageDto) {
        return new ChatMessageDto(chatMessageDto.getSender(), chatMessageDto.getContent());
    }
}
