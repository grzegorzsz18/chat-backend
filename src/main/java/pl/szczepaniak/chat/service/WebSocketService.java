package pl.szczepaniak.chat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageDeliveryException;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import pl.szczepaniak.chat.exceptions.ConversationNotFoundException;
import pl.szczepaniak.chat.exceptions.UserNotFoundException;
import pl.szczepaniak.chat.model.ConversationRepositoryCRUD;
import pl.szczepaniak.chat.model.MessageRepositoryCRUD;
import pl.szczepaniak.chat.model.UserRepositoryCRUD;
import pl.szczepaniak.chat.model.entity.User;
import pl.szczepaniak.chat.service.dto.MessageDTO;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class WebSocketService {

    private UserRepositoryCRUD userRepositoryCRUD;
    private ConversationRepositoryCRUD conversationRepositoryCRUD;
    private MessageRepositoryCRUD messageRepositoryCRUD;
    private SimpMessagingTemplate simpMessagingTemplate;
    private MessageService messageService;

    @Autowired
    public WebSocketService(UserRepositoryCRUD userRepositoryCRUD,
                            ConversationRepositoryCRUD conversationRepositoryCRUD,
                            MessageRepositoryCRUD messageRepositoryCRUD,
                            SimpMessagingTemplate simpMessagingTemplate,
                            MessageService messageService) {
        this.userRepositoryCRUD = userRepositoryCRUD;
        this.conversationRepositoryCRUD = conversationRepositoryCRUD;
        this.messageRepositoryCRUD = messageRepositoryCRUD;
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.messageService = messageService;
    }

    public void sendForUsers(MessageDTO m) throws UserNotFoundException, ConversationNotFoundException {
        MessageDTO message = messageService.sendMessage(m);
        message.setConversationId(m.getConversationId());
        Set<User> users = conversationRepositoryCRUD.getUsersByConversationId(message.getConversationId());
        for(User u: users){
            try {
                simpMessagingTemplate.convertAndSend("/user/" + u.getNick(), message);
            }
            catch (MessageDeliveryException e){
                // when user not connected do nothing
            }
        }
    }
}
