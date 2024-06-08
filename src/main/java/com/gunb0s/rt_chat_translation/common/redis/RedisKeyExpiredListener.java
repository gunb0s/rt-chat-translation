package com.gunb0s.rt_chat_translation.common.redis;

import com.gunb0s.rt_chat_translation.chatMessage.entity.ChatMessage;
import com.gunb0s.rt_chat_translation.chatMessage.repository.ChatMessageRepository;
import com.gunb0s.rt_chat_translation.chatRoom.entity.ChatRoom;
import com.gunb0s.rt_chat_translation.chatRoom.entity.repository.ChatRoomRepository;
import com.gunb0s.rt_chat_translation.common.redis.model.ChatRoomMessagesCopy;
import com.gunb0s.rt_chat_translation.common.redis.repository.ChatRoomMessagesCopyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class RedisKeyExpiredListener extends KeyExpirationEventMessageListener {
    ChatRoomRepository chatRoomRepository;
    ChatMessageRepository chatMessageRepository;
    ChatRoomMessagesCopyRepository chatRoomMessagesCopyRepository;

    public RedisKeyExpiredListener(
            RedisMessageListenerContainer listenerContainer,
            ChatRoomMessagesCopyRepository chatRoomMessagesCopyRepository,
            ChatMessageRepository chatMessageRepository,
            ChatRoomRepository chatRoomRepository
    ) {
        super(listenerContainer);
        this.chatRoomMessagesCopyRepository = chatRoomMessagesCopyRepository;
        this.chatRoomRepository = chatRoomRepository;
        this.chatMessageRepository = chatMessageRepository;
    }

    // __keyevent@*__:expired | com.gunb0s.rt_chat_translation.common.redis.model.ChatRoomMessages:85c0c80e-7125-478a-ae36-d9bd1405ee53
    @Override
    public void onMessage(Message message, byte[] pattern) {
        String patternString = new String(pattern);
        if (!patternString.equals("__keyevent@*__:expired")) {
            return;
        }

        String key = new String(message.getBody());
        String[] split = key.split(":");
        String roomId = split[1];

        log.info("Key expired: {}", roomId);

        ChatRoomMessagesCopy chatRoomMessagesCopy = chatRoomMessagesCopyRepository.findById(roomId).orElseThrow();
        ChatRoom chatRoom = chatRoomRepository.findById(roomId).orElseThrow();
        List<ChatMessage> chatMessages = chatRoomMessagesCopy.getChatMessages()
                .stream()
                .peek(msg -> msg.setChatRoom(chatRoom))
                .filter(msg -> msg.getId() == null)
                .toList();

        chatMessageRepository.saveAll(chatMessages);
        chatRoomMessagesCopyRepository.delete(chatRoomMessagesCopy);
    }
}
