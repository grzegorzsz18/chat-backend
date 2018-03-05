package pl.szczepaniak.chat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.szczepaniak.chat.exceptions.ConversationNotFoundException;
import pl.szczepaniak.chat.model.ConversationRepositoryCRUD;
import pl.szczepaniak.chat.model.MessageRepositoryCRUD;
import pl.szczepaniak.chat.model.entity.Conversation;
import pl.szczepaniak.chat.model.entity.Message;
import pl.szczepaniak.chat.service.converter.MessageToDtoConverter;
import pl.szczepaniak.chat.service.dto.MessageDTO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MessageService {

    MessageRepositoryCRUD messageRepositoryCRUD;
    ConversationRepositoryCRUD conversationRepositoryCRUD;

    @Autowired
    public MessageService(MessageRepositoryCRUD messageRepositoryCRUD,
                          ConversationRepositoryCRUD conversationRepositoryCRUD) {
        this.messageRepositoryCRUD = messageRepositoryCRUD;
        this.conversationRepositoryCRUD = conversationRepositoryCRUD;
    }

    public List<MessageDTO> getAllByConversation(Long conversationId, Integer page, Integer limit) throws ConversationNotFoundException {
        Optional<Conversation> conversation = conversationRepositoryCRUD.findOneById(conversationId);
        if(!conversation.isPresent()){
            throw new ConversationNotFoundException();
        }
        Optional<Page<Message>> messages = messageRepositoryCRUD
                .getAllByConversation(conversation.get(), new PageRequest(page, limit));
        if(!messages.isPresent()){
            throw new ConversationNotFoundException();
        }
        return messages.get().getContent()
                .stream()
                .map(MessageToDtoConverter::convert)
                .collect(Collectors.toList());

    }
}