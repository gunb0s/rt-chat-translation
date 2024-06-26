package com.gunb0s.rt_chat_translation.chatRoom.entity.repository;

import com.gunb0s.rt_chat_translation.chatRoom.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, String> {
    @Query("SELECT cr FROM ChatRoom cr JOIN FETCH cr.chatRoomUsers cru JOIN FETCH cru.user u WHERE u.id = :userId")
    List<ChatRoom> findAllByUserId(@Param("userId") String userId);

    @Query("SELECT cr FROM ChatRoom cr JOIN FETCH cr.chatRoomUsers cru JOIN FETCH cru.user u WHERE cr.id = :chatRoomId")
    Optional<ChatRoom> findByChatRoomIdWithUser(@Param("chatRoomId") String chatRoomId);

    @Query("SELECT cr FROM ChatRoom cr JOIN FETCH cr.chatRoomUsers cru JOIN FETCH cru.user u ORDER BY cr.createdDate DESC")
    List<ChatRoom> findRecentChatRooms();
}
