package com.gunb0s.rt_chat_translation.chatMessage.service;

import com.gunb0s.rt_chat_translation.chatMessage.controller.dto.ChatMessageDto;
import com.gunb0s.rt_chat_translation.chatMessage.entity.ChatMessage;
import com.gunb0s.rt_chat_translation.chatMessage.repository.ChatMessageQueryRepository;
import com.gunb0s.rt_chat_translation.chatMessage.repository.ChatMessageRepository;
import com.gunb0s.rt_chat_translation.chatMessage.repository.RedisChatMessageCache;
import com.gunb0s.rt_chat_translation.chatRoom.entity.ChatRoom;
import com.gunb0s.rt_chat_translation.chatRoom.entity.repository.ChatRoomRepository;
import com.gunb0s.rt_chat_translation.messageBroker.MessageBroker;
import com.gunb0s.rt_chat_translation.user.entity.User;
import com.gunb0s.rt_chat_translation.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ChatMessageService {
    private final ChatMessageRepository chatMessageRepository;
    private final UserRepository userRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageQueryRepository chatMessageQueryRepository;
    private final RedisChatMessageCache cache;
    private final MessageBroker messageBroker;

    private static final int MAX_CHAT_MESSAGE_CACHE_SIZE = 50;
    private static final int CHAT_MESSAGE_COMMIT_SIZE = 30;

    @Transactional
    public void saveChatMessage(String chatId, ChatMessageDto chatMessageDto) {
        ChatRoom chatRoom = chatRoomRepository.findById(chatId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 채팅방입니다.")
        );
        User user = userRepository.findById(chatMessageDto.getSender().getId()).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 사용자입니다.")
        );

        ChatMessage chatMessage = ChatMessage.builder()
                .payload(chatMessageDto.getPayload())
                .user(user)
                .createdAt(chatMessageDto.getCreatedAt())
                .build();

        messageBroker.produceMessage(chatRoom, chatMessage);

        if (!cache.containsKey(chatId)) {
            Queue<ChatMessage> chatMessages = new LinkedList<>();
            chatMessages.add(chatMessage);
            cache.put(chatId, chatMessages);
        } else {
            Queue<ChatMessage> chatMessages = cache.get(chatId);
            chatMessages.add(chatMessage);
            if (chatMessages.size() > MAX_CHAT_MESSAGE_CACHE_SIZE) {
                Queue<ChatMessage> commitChatMessageDtos = new LinkedList<>();
                for (int i = 0; i < CHAT_MESSAGE_COMMIT_SIZE; i++) {
                    commitChatMessageDtos.add(chatMessages.poll());
                }
                commitChatMessage(commitChatMessageDtos);
            }
            cache.put(chatId, chatMessages);
        }
    }

    public List<ChatMessage> getMessages(String chatId, Pageable pageable) {
        List<ChatMessage> messages = new ArrayList<>();
        LinkedList<ChatMessage> list = cache.get(chatId);
        if (!cache.containsKey(chatId) || list.isEmpty()) {
            // Cache Miss
            List<ChatMessage> messageList = getMessageFromDB(chatId, pageable)
                    .stream()
                    .sorted(Comparator.comparing(ChatMessage::getCreatedDate))
                    .toList();
            if (!messageList.isEmpty()) {
                Queue<ChatMessage> chatMessageWarmUp = new LinkedList<>(messageList);
                cache.put(chatId, chatMessageWarmUp);
                messages = messageList;
            }
        } else {
            // Cache Hit
            messages = list
                    .stream()
                    .toList();
        }

        return messages;
    }

    private List<ChatMessage> getMessageFromDB(String chatId, Pageable pageable) {
        return chatMessageQueryRepository
                .findAllByChatRoomId(chatId, pageable)
                .stream()
                .toList();
    }

    private void commitChatMessage(Queue<ChatMessage> chatMessages) {
        chatMessageRepository.saveAll(chatMessages);
    }
}
