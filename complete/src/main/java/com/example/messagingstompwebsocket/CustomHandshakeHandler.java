package com.example.messagingstompwebsocket;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.net.URI;
import java.security.Principal;
import java.util.List;
import java.util.Map;

public class CustomHandshakeHandler extends DefaultHandshakeHandler {
    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        final ServletServerHttpRequest httpRequest = (ServletServerHttpRequest) request;
        final String playerId = httpRequest.getServletRequest().getParameter("playerId");

        return new User(playerId);
    }
}
