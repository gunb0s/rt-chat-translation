package com.gunb0s.rt_chat_translation.chatMessage.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@DataJpaTest
class ChatMessageRepositoryTest {
    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @Test
    @DisplayName("채팅방 아이디로 채팅 메시지 조회 created_date 순으로 정렬하는 쿼리 확인")
    void findAllByChatRoomId() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Order.desc("createdDate")));

        chatMessageRepository.findAllByChatRoomId("87e71ef8-b0cd-4207-a15a-195c8b1f7719", pageable);
    }
}
