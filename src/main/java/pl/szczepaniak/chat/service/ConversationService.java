package pl.szczepaniak.chat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.szczepaniak.chat.exceptions.ConversationAlreadyExistsException;
import pl.szczepaniak.chat.exceptions.ConversationNotFoundException;
import pl.szczepaniak.chat.exceptions.UserNotFoundException;
import pl.szczepaniak.chat.model.ConversationRepositoryCRUD;
import pl.szczepaniak.chat.model.UserRepositoryCRUD;
import pl.szczepaniak.chat.model.entity.Conversation;
import pl.szczepaniak.chat.model.entity.User;
import pl.szczepaniak.chat.service.converter.ConversationToDtoConverter;
import pl.szczepaniak.chat.service.dto.ConversationDTO;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ConversationService {

    private ConversationRepositoryCRUD conversationRepository;
    private UserService userService;
    private UserRepositoryCRUD userRepositoryCRUD;

    @Autowired
    public ConversationService(ConversationRepositoryCRUD conversationRepositoryCRUD,
                               UserService userService,
                               UserRepositoryCRUD userRepositoryCRUD) {
        this.conversationRepository = conversationRepositoryCRUD;
        this.userService = userService;
        this.userRepositoryCRUD = userRepositoryCRUD;
    }

    public List<ConversationDTO> getConversations(String email, Integer page, Integer limit) throws UserNotFoundException {
        User user = userRepositoryCRUD.getUserByEmail(email)
                .orElseThrow(UserNotFoundException::new);
        return conversationRepository.findByUsersContains(user, new PageRequest(page, limit))
                .get().getContent()
                .stream()
                .map(conversation -> ConversationToDtoConverter.convert(conversation))
                .collect(Collectors.toList());
    }

    public ConversationDTO addConversationIfNotExist(List<User> users) throws UserNotFoundException, ConversationNotFoundException {
        Set<User> usersDb = new HashSet<>();
        for(User u: users){
            User user = userRepositoryCRUD.getUserByNick(u.getNick())
                    .orElseThrow(UserNotFoundException::new);
            usersDb.add(user);
        }
        List<User> userList = usersDb.stream().collect(Collectors.toList());
        try {
            return searchConversation(userList);
        }
        catch (ConversationNotFoundException e){
            conversationRepository.save(Conversation.builder()
                    .users(usersDb)
                    .build());
            return searchConversation(userList);}
    }

    private ConversationDTO searchConversation(List<User> userList) throws ConversationNotFoundException {
        List<Conversation> conversations = conversationRepository.findByUsersContainsAndUsersContains(
                userList.get(0), userList.get(1))
                .orElseThrow(ConversationNotFoundException::new);
        Conversation conversation = conversations
                .stream().filter(conversation1 -> conversation1.getUsers().size() ==2).findFirst()
                .orElseThrow(ConversationNotFoundException::new);
        return ConversationToDtoConverter.convert(conversation);}
}
