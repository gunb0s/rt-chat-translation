package com.gunb0s.rt_chat_translation.chatMessage.controller;

import com.gunb0s.rt_chat_translation.chatMessage.controller.dto.ChatMessageDto;
import com.gunb0s.rt_chat_translation.chatMessage.entity.ChatMessage;
import com.gunb0s.rt_chat_translation.chatMessage.service.ChatMessageService;
import com.gunb0s.rt_chat_translation.common.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/chat-message")
@RequiredArgsConstructor
public class ChatMessageController {
    private final ChatMessageService chatMessageService;

    @MessageMapping("/{chatId}")
    @SendTo("/sub/channel/{chatId}")
    public ChatMessageDto message(@DestinationVariable String chatId, ChatMessageDto chatMessageDto) {
        chatMessageService.saveChatMessage(chatId, chatMessageDto);
        return chatMessageDto;
    }

    @GetMapping()
    public ResponseEntity<?> getChatRoomMessages(@RequestParam String chatRoomId, Pageable pageable) {
        List<ChatMessage> chatMessages = chatMessageService.getMessages(chatRoomId, pageable);
        List<ChatMessageDto> list = chatMessages
                .stream()
                .map(ChatMessageDto::new)
                .toList();

        return ResponseEntity
                .ok(
                        ResponseDto.builder()
                                .status(HttpStatus.OK.value())
                                .data(list)
                                .build()
                );
    }
}
