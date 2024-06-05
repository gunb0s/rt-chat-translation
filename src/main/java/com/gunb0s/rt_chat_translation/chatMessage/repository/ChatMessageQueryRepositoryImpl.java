package com.gunb0s.rt_chat_translation.chatMessage.repository;

import com.gunb0s.rt_chat_translation.chatMessage.entity.ChatMessage;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.gunb0s.rt_chat_translation.chatMessage.entity.QChatMessage.chatMessage;

@Repository
public class ChatMessageQueryRepositoryImpl implements ChatMessageQueryRepository {
    private JPAQueryFactory queryFactory;

    public ChatMessageQueryRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<ChatMessage> findAllByChatRoomId(String chatRoomId, Pageable pageable) {
        List<ChatMessage> results = queryFactory
                .selectFrom(chatMessage)
                .join(chatMessage.user).fetchJoin()
                .where(chatMessage.chatRoom.id.eq(chatRoomId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory.select(chatMessage.count())
                .where(chatMessage.chatRoom.id.eq(chatRoomId))
                .from(chatMessage);

        return PageableExecutionUtils.getPage(results, pageable, countQuery::fetchOne);
    }
}
