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
        Optional<User> user = userRepositoryCRUD.getUserByEmail(email);
        if( !user.isPresent()){
            throw new UserNotFoundException();
        }
        return conversationRepository.findByUsersContains(user.get(), new PageRequest(page, limit))
                .get().getContent()
                .stream()
                .map(conversation -> ConversationToDtoConverter.convert(conversation))
                .collect(Collectors.toList());
    }

    public ConversationDTO addConversationIfNotExist(List<User> users) throws UserNotFoundException, ConversationAlreadyExistsException, ConversationNotFoundException {
        Set<User> usersDb = new HashSet<>();
        for(User u: users){
            Optional<User> user = userRepositoryCRUD.getUserByNick(u.getNick());
            if(!user.isPresent()){
                throw new UserNotFoundException();
            }
            usersDb.add(user.get());
        }
        List<User> userList = usersDb.stream().collect(Collectors.toList());
        try {
            return searchConversation(userList);
        }
        catch (ConversationNotFoundException e){
            conversationRepository.save(Conversation.builder()
                    .users(usersDb)
                    .build());
            return searchConversation(userList);
        }



    }

    private ConversationDTO searchConversation(List<User> userList) throws ConversationNotFoundException {
        Optional<List<Conversation>> conversations = conversationRepository.findByUsersContainsAndUsersContains(
                userList.get(0), userList.get(1));
        if(!conversations.isPresent()) {
            throw new ConversationNotFoundException();
        }
            Optional<Conversation> conversation = conversations.get()
                            .stream().filter(conversation1 -> conversation1.getUsers().size() ==2).findFirst();
            if(!conversation.isPresent()){
                throw new ConversationNotFoundException();
            }
            return ConversationToDtoConverter.convert(conversation.get());
        }
}
