package com.example.messagingstompwebsocket;

import lombok.Data;

@Data
public class ChatMessage {

    private String fromUser;
    private String toUser;
    private String message;
}
