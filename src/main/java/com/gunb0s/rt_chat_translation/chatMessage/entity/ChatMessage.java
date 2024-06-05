package com.gunb0s.rt_chat_translation.chatMessage.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gunb0s.rt_chat_translation.chatRoom.entity.ChatRoom;
import com.gunb0s.rt_chat_translation.user.entity.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "chat_messages")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@Getter
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime updatedDate;

    @Column()
    private String payload;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_room_id")
    private ChatRoom chatRoom;

    @Builder
    public ChatMessage(String payload, LocalDateTime createdAt, User user, ChatRoom chatRoom) {
        this.payload = payload;
        this.user = user;
        this.chatRoom = chatRoom;
        this.createdDate = createdAt;
    }
}
