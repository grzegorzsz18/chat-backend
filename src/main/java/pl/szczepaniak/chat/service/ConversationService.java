package pl.szczepaniak.chat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.szczepaniak.chat.exceptions.UserNotFoundException;
import pl.szczepaniak.chat.model.ConversationRepositoryCRUD;
import pl.szczepaniak.chat.model.UserRepositoryCRUD;
import pl.szczepaniak.chat.model.entity.User;
import pl.szczepaniak.chat.service.converter.ConversationToDtoConverter;
import pl.szczepaniak.chat.service.dto.ConversationDTO;

import java.util.List;
import java.util.Optional;
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
}
