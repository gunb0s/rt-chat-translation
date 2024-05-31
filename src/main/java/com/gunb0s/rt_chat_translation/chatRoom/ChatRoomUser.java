package com.gunb0s.rt_chat_translation.chatRoom;

import com.gunb0s.rt_chat_translation.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(
        name = "chat_room_users",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_chat_room_user",
                        columnNames = {"user_id", "chat_room_id"}
                )
        }
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatRoomUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_room_id")
    private ChatRoom chatRoom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public ChatRoomUser(ChatRoom chatRoom, User user) {
        this.chatRoom = chatRoom;
        this.user = user;
    }
}
