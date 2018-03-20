package pl.szczepaniak.chat.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import pl.szczepaniak.chat.service.WebSocketService;

@Controller
public class WebSocketController {

    private WebSocketService webSocketService;

    @Autowired
    public WebSocketController(WebSocketService webSocketService) {
        this.webSocketService = webSocketService;
    }

    @MessageMapping("/send/message/{conversationId}")
    public void onReciveMessage(@DestinationVariable Long conversationId, String message){
        this.webSocketService.sendForUsers(conversationId, message);
    }
}
