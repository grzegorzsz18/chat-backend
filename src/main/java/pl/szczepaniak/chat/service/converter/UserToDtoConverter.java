package pl.szczepaniak.chat.service.converter;

import pl.szczepaniak.chat.model.entity.User;
import pl.szczepaniak.chat.service.dto.UserDTO;

public class UserToDtoConverter {

    private UserToDtoConverter(){

    }

    public static UserDTO convert(User user){
        return UserDTO.builder().nick(user.getNick()).email(user.getEmail()).build();
    }
}
