package com.gunb0s.rt_chat_translation.socket;

import com.gunb0s.rt_chat_translation.chatRoom.ChatRoom;
import com.gunb0s.rt_chat_translation.chatRoom.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

@Component
@RequiredArgsConstructor
public class WebSocketEventListener {
    private final ChatRoomRepository chatRoomRepository;

    @EventListener
    public void handleSessionSubscribeEvent(SessionSubscribeEvent event) {
        SimpMessageHeaderAccessor accessor = SimpMessageHeaderAccessor.wrap(event.getMessage());
        String destination = accessor.getDestination();

        if (destination != null && destination.startsWith("/sub/channel/")) {
            String chatId = destination.split("/")[3];
            if (!chatRoomRepository.existsById(chatId)) {
                ChatRoom chatRoom = ChatRoom
                        .builder()
                        .id(chatId)
                        .build();
                chatRoomRepository.save(chatRoom);
            }

            // chatRoom 에 User 도 저장.
        }
    }
}
