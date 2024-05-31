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

    public <T> T get(String url, String accessToken, Class<T> responseClass) {
        log.info("[GET] url: {}", url);
        return webClient.get()
                .uri(url)
                .header("Authorization", "Bearer " + accessToken)
                .retrieve()
                .bodyToMono(responseClass)
                .block();

    }

    public <T> T post(String url, Object body, Class<T> responseClass) {
        log.info("[POST] url: {}", url);
        if (body == null) {
            return webClient.post()
                    .uri(url)
                    .header("Accept", "application/json")
                    .retrieve()
                    .bodyToMono(responseClass)
                    .block();
        }

        return webClient.post()
                .uri(url)
                .header("Accept", "application/json")
                .bodyValue(body)
                .retrieve()
                .bodyToMono(responseClass)
                .block();
    }
}
