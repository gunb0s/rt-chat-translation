package com.gunb0s.rt_chat_translation.chatRoom;

import com.gunb0s.rt_chat_translation.common.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "chat_rooms")
public class ChatRoom extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
}
