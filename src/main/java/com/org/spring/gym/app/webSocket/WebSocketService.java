package com.org.spring.gym.app.webSocket;

import com.org.spring.gym.app.webSocket.DTO.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketService {

    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    public WebSocketService(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    public void notify(String message){
        Response response = new Response(message);
        simpMessagingTemplate.convertAndSend("/topic/public-messages", response);
    }

    public void notifyEmployee(String username,String message){
        Response response = new Response(message);
        simpMessagingTemplate.convertAndSendToUser(username,"/queue/private-messages", response);
    }
}
