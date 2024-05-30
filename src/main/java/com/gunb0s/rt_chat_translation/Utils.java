package com.gunb0s.rt_chat_translation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gunb0s.rt_chat_translation.socket.HelloMessage;

public class Utils {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private Utils() {
    }

    public static HelloMessage getObject(final String message) throws Exception {
        return objectMapper.readValue(message, HelloMessage.class);
    }

    public static String getString(final HelloMessage message) throws Exception {
        return objectMapper.writeValueAsString(message);
    }
}
