package com.gunb0s.rt_chat_translation.common;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
@Slf4j
public class HttpService {
    private final WebClient webClient;

    public <T> T post(String code, String url, Object body, Class<T> responseClass) {


        log.info("url: {}", url);

        return webClient.post()
                .uri(url)
                .header("Accept", "application/json")
                .bodyValue(body)
                .retrieve()
                .bodyToMono(responseClass)
                .block();
    }
}
