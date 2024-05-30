package com.gunb0s.rt_chat_translation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class RtChatTranslationApplication {

    public static void main(String[] args) {
        SpringApplication.run(RtChatTranslationApplication.class, args);
    }

}
