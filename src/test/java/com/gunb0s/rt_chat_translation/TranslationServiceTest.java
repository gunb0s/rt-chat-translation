package com.gunb0s.rt_chat_translation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TranslationServiceTest {
    @Autowired
    private TranslationService translationService;

    @Test
    void translateText() {
        String text = "Hello";
        String targetLanguage = "ko";
        String translatedText = translationService.translateText(text, targetLanguage);
        System.out.println(translatedText);
    }
}
