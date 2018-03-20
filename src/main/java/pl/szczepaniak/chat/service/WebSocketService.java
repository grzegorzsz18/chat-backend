package pl.szczepaniak.chat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageDeliveryException;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import pl.szczepaniak.chat.exceptions.UserNotFoundException;
import pl.szczepaniak.chat.model.ConversationRepositoryCRUD;
import pl.szczepaniak.chat.model.MessageRepositoryCRUD;
import pl.szczepaniak.chat.model.UserRepositoryCRUD;
import pl.szczepaniak.chat.model.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class WebSocketService {

    private UserRepositoryCRUD userRepositoryCRUD;
    private ConversationRepositoryCRUD conversationRepositoryCRUD;
    private MessageRepositoryCRUD messageRepositoryCRUD;
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    public WebSocketService(UserRepositoryCRUD userRepositoryCRUD,
                            ConversationRepositoryCRUD conversationRepositoryCRUD,
                            MessageRepositoryCRUD messageRepositoryCRUD,
                            SimpMessagingTemplate simpMessagingTemplate) {
        this.userRepositoryCRUD = userRepositoryCRUD;
        this.conversationRepositoryCRUD = conversationRepositoryCRUD;
        this.messageRepositoryCRUD = messageRepositoryCRUD;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    public void sendForUsers(Long conversationId, String message){
        Set<User> users = conversationRepositoryCRUD.getUsersByConversationId(conversationId);
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
