package com.gunb0s.rt_chat_translation.common.redis.repository;

import com.gunb0s.rt_chat_translation.common.redis.model.ChatRoomMessagesCopy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRoomMessagesCopyRepository extends CrudRepository<ChatRoomMessagesCopy, String> {
}
