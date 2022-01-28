package com.org.spring.gym.app.webSocket.DTO;

public class Response {
    String messageContent;

    public Response() {
    }

    public Response(String messageContent) {
        this.messageContent = messageContent;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }
}
