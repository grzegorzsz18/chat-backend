package pl.szczepaniak.chat.service.converter;

import pl.szczepaniak.chat.model.entity.Conversation;
import pl.szczepaniak.chat.service.dto.ConversationDTO;

import java.util.stream.Collectors;

public class ConversationToDtoConverter {

    private ConversationToDtoConverter(){

    }

    public static ConversationDTO convert(Conversation conversation){
        return ConversationDTO.builder()
                .id(conversation.getId())
                .users(conversation.getUsers().stream().map(user -> UserToDtoConverter.convert(user)).collect(Collectors.toList()))
                .build();
    }
}
