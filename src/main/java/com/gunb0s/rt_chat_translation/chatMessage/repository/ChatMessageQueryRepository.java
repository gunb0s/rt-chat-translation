package com.gunb0s.rt_chat_translation.chatMessage.repository;

import com.gunb0s.rt_chat_translation.chatMessage.entity.ChatMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ChatMessageQueryRepository {
    Page<ChatMessage> findAllByChatRoomId(
            String chatRoomId,
            Pageable pageable);
}
