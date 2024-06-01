package com.gunb0s.rt_chat_translation.chatRoom.entity.repository;

import com.gunb0s.rt_chat_translation.chatRoom.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, String> {
}
