package pl.szczepaniak.chat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
}
