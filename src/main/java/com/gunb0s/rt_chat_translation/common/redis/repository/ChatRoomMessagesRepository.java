package com.gunb0s.rt_chat_translation.common.redis.repository;

import com.gunb0s.rt_chat_translation.common.redis.model.ChatRoomMessages;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRoomMessagesRepository extends CrudRepository<ChatRoomMessages, String> {
}
