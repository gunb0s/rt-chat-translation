package com.gunb0s.rt_chat_translation.socket;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
@RequiredArgsConstructor
public class MessageController {

    /**
     * If a message is sent to /hello destination, the message() method is called.
     * The return value is broadcast to all subscribers of /sub/channel, as specified in the @SendTo annotation.
     */
    @MessageMapping("/hello")
    @SendTo("/sub/channel")
    public Greeting message(HelloMessage message) throws InterruptedException {
        Thread.sleep(1000);
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }
}
