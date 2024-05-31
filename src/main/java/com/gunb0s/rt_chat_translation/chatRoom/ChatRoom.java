package com.gunb0s.rt_chat_translation.chatRoom;

import com.gunb0s.rt_chat_translation.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "chat_rooms")
@NoArgsConstructor
public class ChatRoom extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Builder
    public ChatRoom(String id) {
        this.id = id;
    }
}
