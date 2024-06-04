package com.gunb0s.rt_chat_translation.socket.service;

import com.gunb0s.rt_chat_translation.chatMessage.controller.dto.ChatMessageDto;
import com.gunb0s.rt_chat_translation.chatMessage.entity.ChatMessage;
import com.gunb0s.rt_chat_translation.chatMessage.repository.ChatMessageRepository;
import com.gunb0s.rt_chat_translation.chatRoom.entity.ChatRoom;
import com.gunb0s.rt_chat_translation.chatRoom.entity.repository.ChatRoomRepository;
import com.gunb0s.rt_chat_translation.user.entity.User;
import com.gunb0s.rt_chat_translation.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MessageService {
    private final ChatMessageRepository chatMessageRepository;
    private final UserRepository userRepository;
    private final ChatRoomRepository chatRoomRepository;

    @Transactional
    public void saveChatMessage(String chatId, ChatMessageDto chatMessageDto) {
        User user = userRepository.findById(chatMessageDto.getSender().getId()).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 사용자입니다.")
        );
        ChatRoom chatRoom = chatRoomRepository.findById(chatId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 채팅방입니다.")
        );

        ChatMessage chatMessage = ChatMessage.builder()
                .payload(chatMessageDto.getPayload())
                .user(user)
                .chatRoom(chatRoom)
                .createdAt(chatMessageDto.getCreatedAt())
                .build();
        chatMessageRepository.save(chatMessage);
    }
}
