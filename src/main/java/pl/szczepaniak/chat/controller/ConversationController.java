package pl.szczepaniak.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.szczepaniak.chat.exceptions.UserNotFoundException;
import pl.szczepaniak.chat.model.entity.Conversation;
import pl.szczepaniak.chat.service.ConversationService;
import pl.szczepaniak.chat.service.dto.ConversationDTO;

import java.util.List;

@RestController
@RequestMapping("/conversation")
public class ConversationController {

    @Autowired
    ConversationService conversationService;

    @GetMapping
    public List<ConversationDTO> get() throws UserNotFoundException {
        return conversationService.getConversations("jan@wp.pl",0,10);
    }
}
