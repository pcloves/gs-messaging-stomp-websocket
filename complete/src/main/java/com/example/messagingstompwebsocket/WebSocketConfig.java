package com.example.messagingstompwebsocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableStompBrokerRelay("/topic")
                .setRelayHost("192.168.50.104")
                .setVirtualHost("zhengtongshan")
                .setSystemLogin("user")
                .setSystemPasscode("xcyg580Wgr@rabbitmq")
                .setClientLogin("user")
                .setClientPasscode("xcyg580Wgr@rabbitmq")
                .setAutoStartup(true);
//        config.enableSimpleBroker("/topic");

        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/gs-guide-websocket")
				.setHandshakeHandler(new CustomHandshakeHandler());
    }

}
