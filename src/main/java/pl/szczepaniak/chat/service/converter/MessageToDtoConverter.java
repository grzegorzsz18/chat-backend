package pl.szczepaniak.chat.service.converter;

import pl.szczepaniak.chat.model.entity.Message;
import pl.szczepaniak.chat.service.dto.MessageDTO;

public class MessageToDtoConverter {

    private MessageToDtoConverter() {
    }

    public static MessageDTO convert(Message message){
        return MessageDTO.builder()
                .time(message.getTime())
                .text(message.getText())
                .isDisplayed(message.isDisplayed())
                .autor(message.getUser().getNick())
                .build();
    }
}
