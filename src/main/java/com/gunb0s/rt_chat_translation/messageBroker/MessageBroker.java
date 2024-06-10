package com.gunb0s.rt_chat_translation.messageBroker;

import com.gunb0s.rt_chat_translation.chatMessage.entity.ChatMessage;

public interface MessageBroker {
    void createChatRoom(String subscriptionTopic);

    void produceMessage(String subscriptionTopic, ChatMessage chatMessage);
}
