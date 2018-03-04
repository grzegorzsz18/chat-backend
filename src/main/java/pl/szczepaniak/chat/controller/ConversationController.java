package pl.szczepaniak.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/user")
    public List<ConversationDTO> getUsersConv(@RequestParam("email") String email,
                                              @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                              @RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit)
                                                throws UserNotFoundException {
        return conversationService.getConversations(email,page,limit);
    }
}
