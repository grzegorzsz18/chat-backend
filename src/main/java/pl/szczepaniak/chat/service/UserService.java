package pl.szczepaniak.chat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.szczepaniak.chat.exceptions.EmailAlreadyRegistered;
import pl.szczepaniak.chat.exceptions.NickAlreadyRegistered;
import pl.szczepaniak.chat.exceptions.UserNotFoundException;
import pl.szczepaniak.chat.model.UserRepositoryCRUD;
import pl.szczepaniak.chat.model.entity.User;

import java.util.Optional;

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
}
