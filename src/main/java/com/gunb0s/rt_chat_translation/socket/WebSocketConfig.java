package com.gunb0s.rt_chat_translation.socket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker // enables WebSocket message handling, backed by a message broker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // enable simple memory-based message broker to carry the messages back to the client
        // on destinations prefixed with /sub
        registry.enableSimpleBroker("/sub");
        // It also designates the /pub prefix for messages that are bound for methods
        // annotated with @MessageMapping
        registry.setApplicationDestinationPrefixes("/pub");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry
                .addEndpoint("/ws") // 웹 소켓 서버의 엔드포인트
                .setAllowedOrigins("http://localhost:3000")
                .withSockJS(); // 클라이언트에서 웹 소켓 서버에 요청 시 모든 요청을 수용한다.
    }
}
