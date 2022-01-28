package com.org.spring.gym.app.webSocket;

import com.org.spring.gym.app.webSocket.DTO.Message;
import com.org.spring.gym.app.webSocket.DTO.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import java.security.Principal;


@Controller
public class MessageController {

    @Autowired
    private WebSocketService webSocketService;

    @MessageMapping("/message")
    @SendTo("/topic/public-messages")
    public Response getMessage(Message message){
        return new Response(HtmlUtils.htmlEscape(message.getMessageContent()));
    }

    @MessageMapping("/private-message")
    @SendToUser("/queue/private-messages")
    public Response getPrivateMessage(Message message , Principal principal){
        return new Response("Hello from :  "+ HtmlUtils.htmlEscape(message.getMessageContent()));
    }

//    @PostMapping("/send-message")
//    public void sendMessage(@RequestBody Message message) {
//        webSocketService.notify(message.getMessageContent());
//    }
//
//    @PostMapping("/send-private-message/{username}")
//    public void sendPrivateMessage(@PathVariable final String username,
//                                   @RequestBody final Message message) {
//        webSocketService.notifyEmployee(username, message.getMessageContent());
//    }



}
