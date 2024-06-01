package com.gunb0s.rt_chat_translation.chatRoom.service;

import com.gunb0s.rt_chat_translation.chatRoom.entity.ChatRoom;
import com.gunb0s.rt_chat_translation.chatRoom.entity.ChatRoomUser;
import com.gunb0s.rt_chat_translation.chatRoom.entity.repository.ChatRoomRepository;
import com.gunb0s.rt_chat_translation.user.entity.User;
import com.gunb0s.rt_chat_translation.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChatRoomService {
    private final ChatRoomRepository chatRoomService;
    private final UserRepository userRepository;
    private final ChatRoomRepository chatRoomRepository;

    @Transactional
    public ChatRoom createChatRoom(String userId) {
        ChatRoom chatRoom = ChatRoom.builder()
                .build();

        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        ChatRoomUser chatRoomUser = ChatRoomUser.builder()
                .chatRoom(chatRoom)
                .user(user)
                .build();

        chatRoom.addUser(chatRoomUser);
        return chatRoomService.save(chatRoom);
    }

    public List<ChatRoom> getMyChatRooms(String userId) {
        return chatRoomRepository.findAllByUserId(userId);
    }
}
