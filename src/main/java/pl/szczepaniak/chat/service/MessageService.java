package pl.szczepaniak.chat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.szczepaniak.chat.exceptions.ConversationNotFoundException;
import pl.szczepaniak.chat.exceptions.UserNotFoundException;
import pl.szczepaniak.chat.model.ConversationRepositoryCRUD;
import pl.szczepaniak.chat.model.MessageRepositoryCRUD;
import pl.szczepaniak.chat.model.UserRepositoryCRUD;
import pl.szczepaniak.chat.model.entity.Conversation;
import pl.szczepaniak.chat.model.entity.Message;
import pl.szczepaniak.chat.model.entity.User;
import pl.szczepaniak.chat.service.converter.MessageToDtoConverter;
import pl.szczepaniak.chat.service.dto.MessageDTO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MessageService {

    MessageRepositoryCRUD messageRepositoryCRUD;
    ConversationRepositoryCRUD conversationRepositoryCRUD;
    UserRepositoryCRUD userRepositoryCRUD;

    @Autowired
    public MessageService(MessageRepositoryCRUD messageRepositoryCRUD,
                          ConversationRepositoryCRUD conversationRepositoryCRUD,
                          UserRepositoryCRUD userRepositoryCRUD) {
        this.messageRepositoryCRUD = messageRepositoryCRUD;
        this.conversationRepositoryCRUD = conversationRepositoryCRUD;
        this.userRepositoryCRUD = userRepositoryCRUD;
    }

    public List<MessageDTO> getAllByConversation(Long conversationId, Integer page, Integer limit) throws ConversationNotFoundException {
        Conversation conversation = conversationRepositoryCRUD.findOneById(conversationId)
                .orElseThrow(ConversationNotFoundException::new);

        return messageRepositoryCRUD.getAllByConversationOrderByTimeDesc(conversation, new PageRequest(page, limit))
                .orElseThrow(ConversationNotFoundException::new)
                .getContent()
                .stream()
                .map(MessageToDtoConverter::convert)
                .collect(Collectors.toList());

    }


    public MessageDTO sendMessage(MessageDTO m) throws UserNotFoundException, ConversationNotFoundException {
        User user = userRepositoryCRUD.getUserByNick(m.getAutor())
                .orElseThrow(UserNotFoundException::new);
        Conversation conversation = conversationRepositoryCRUD.findOneById(m.getConversationId())
                .orElseThrow(ConversationNotFoundException::new);
        Message message = Message.builder()
                .conversation(conversation)
                .isDisplayed(false)
                .text(m.getText())
                .user(user)
                .time(System.currentTimeMillis())
                .build();
        messageRepositoryCRUD.save(message);
        return MessageToDtoConverter.convert(message);
    }
}