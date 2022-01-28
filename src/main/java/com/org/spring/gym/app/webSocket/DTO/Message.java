package com.org.spring.gym.app.webSocket.DTO;

public class Message {
    String messageContent;

    public Message() {
    }
    public Message(String messageContent) {
        this.messageContent = messageContent;
    }
    public String getMessageContent() {
        return messageContent;
    }
    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }
}
