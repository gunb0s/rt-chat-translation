package com.gunb0s.rt_chat_translation;

import com.gunb0s.rt_chat_translation.google_translation.TranslationService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TranslationServiceTest {
    @Autowired
    private TranslationService translationService;

    @Test
    @Disabled
    void translateText() {
        String text = "Hello";
        String targetLanguage = "ko";
        String translatedText = translationService.translateText(text, targetLanguage);

        assertThat(translatedText).isEqualTo("안녕하세요");
    }
}
