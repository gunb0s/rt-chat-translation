package com.gunb0s.rt_chat_translation.messageBroker;

import com.gunb0s.rt_chat_translation.chatMessage.entity.ChatMessage;
import com.gunb0s.rt_chat_translation.chatRoom.entity.ChatRoom;

public interface MessageBroker {
    void createChatRoom(String subscriptionTopic);

    void produceMessage(ChatRoom chatRoom, ChatMessage chatMessage);
}
