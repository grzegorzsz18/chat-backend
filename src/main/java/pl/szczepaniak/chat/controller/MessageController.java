package pl.szczepaniak.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.szczepaniak.chat.exceptions.ConversationNotFoundException;
import pl.szczepaniak.chat.exceptions.UserNotFoundException;
import pl.szczepaniak.chat.service.MessageService;
import pl.szczepaniak.chat.service.dto.MessageDTO;

import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {

    private MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/conversation")
    public ResponseEntity<List<MessageDTO>> getMessages(@RequestParam("conversation_id") Long id,
                                                        @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                                        @RequestParam(value = "limit" , required = false, defaultValue = "10") Integer limit) throws ConversationNotFoundException {
        return new ResponseEntity<>(messageService.getAllByConversation(id, page, limit), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity sendMessage(@RequestBody String textMessage,
                                      @RequestParam(value = "userEmail") String userEmail,
                                      @RequestParam(value = "conversationId") Long conversationId) throws UserNotFoundException, ConversationNotFoundException {
        messageService.sendMessage(textMessage, userEmail, conversationId);
        return new ResponseEntity(HttpStatus.CREATED);
    }


}
