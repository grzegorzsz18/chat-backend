package pl.szczepaniak.chat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.szczepaniak.chat.exceptions.EmailAlreadyRegistered;
import pl.szczepaniak.chat.exceptions.NickAlreadyRegistered;
import pl.szczepaniak.chat.exceptions.UserNotFoundException;
import pl.szczepaniak.chat.model.UserRepositoryCRUD;
import pl.szczepaniak.chat.model.entity.User;
import pl.szczepaniak.chat.service.converter.UserToDtoConverter;
import pl.szczepaniak.chat.service.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    UserRepositoryCRUD userRepositoryCRUD;

    @Autowired
    public UserService(UserRepositoryCRUD userRepositoryCRUD) {
        this.userRepositoryCRUD = userRepositoryCRUD;
    }

    public long getIdByEmail(String email) throws UserNotFoundException {
        Optional<User> user = userRepositoryCRUD.getUserByEmail(email);
        if(user.isPresent()){
            return user.get().getId();
        }
        else {
            throw new UserNotFoundException();
        }
    }

    public void addNewUser(String email, String password, String nick) throws EmailAlreadyRegistered, NickAlreadyRegistered {
        if(userRepositoryCRUD.getUserByEmail(email).isPresent()){
            throw new EmailAlreadyRegistered();
        }
        if(userRepositoryCRUD.getUserByNick(nick).isPresent()){
            throw new NickAlreadyRegistered();
        }

        userRepositoryCRUD.save(User.builder().password(password).nick(nick).email(email).enabled(true).build());
    }

    public String getUserNick(String email) throws UserNotFoundException {
        Optional<User> user = userRepositoryCRUD.getUserByEmail(email);
        if(user.isPresent()){
            return user.get().getNick();
        }
        else {
            throw new UserNotFoundException();
        }
    }

    public List<UserDTO> getAllUsers(String nick, Integer page, Integer limit) throws UserNotFoundException {
        Optional<Page<User>> users = userRepositoryCRUD.findByNickContaining(nick, new PageRequest(page, limit));
        if (users.isPresent()){
            return users.get().getContent().stream()
                    .map(user -> UserToDtoConverter.convert(user))
                    .collect(Collectors.toList());
        }
        else {
            throw new UserNotFoundException();
        }
    }
}
