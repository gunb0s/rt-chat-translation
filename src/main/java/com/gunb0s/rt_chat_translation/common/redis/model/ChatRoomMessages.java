package com.gunb0s.rt_chat_translation.common.redis.model;

import com.gunb0s.rt_chat_translation.chatMessage.entity.ChatMessage;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import java.util.LinkedList;

@Data
@Builder
@RedisHash(timeToLive = 60 * 10)
public class ChatRoomMessages {
    private String id;
    private LinkedList<ChatMessage> chatMessages;
}
