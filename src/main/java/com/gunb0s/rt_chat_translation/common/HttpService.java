package com.gunb0s.rt_chat_translation.common;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor
@Slf4j
public class HttpService {
    private final WebClient webClient;

    public String post(String code) {
        String url = UriComponentsBuilder.fromHttpUrl("https://github.com/login/oauth/access_token")
                .queryParam("client_id", "")
                .queryParam("client_secret", "")
                .queryParam("code", code)
                .build()
                .toUriString();

        log.info("url: {}", url);

        return webClient.post()
                .uri(url)
                .header("Accept", "application/json")
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
