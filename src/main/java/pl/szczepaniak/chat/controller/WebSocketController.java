package pl.szczepaniak.chat.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import pl.szczepaniak.chat.exceptions.ConversationNotFoundException;
import pl.szczepaniak.chat.exceptions.UserNotFoundException;
import pl.szczepaniak.chat.service.WebSocketService;
import pl.szczepaniak.chat.service.dto.MessageDTO;

@Controller
public class WebSocketController {

    private WebSocketService webSocketService;

    @Autowired
    public WebSocketController(WebSocketService webSocketService) {
        this.webSocketService = webSocketService;
    }

    @MessageMapping("/send/message")
    public void onReciveMessage(MessageDTO message) throws UserNotFoundException, ConversationNotFoundException {
        this.webSocketService.sendForUsers(message);
    }
}
