package pl.szczepaniak.chat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.szczepaniak.chat.exceptions.EmailAlreadyRegistered;
import pl.szczepaniak.chat.exceptions.NickAlreadyRegistered;
import pl.szczepaniak.chat.exceptions.UserNotFoundException;
import pl.szczepaniak.chat.model.UserRepositoryCRUD;
import pl.szczepaniak.chat.model.entity.User;
import pl.szczepaniak.chat.service.converter.UserToDtoConverter;
import pl.szczepaniak.chat.service.dto.UserDTO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    UserRepositoryCRUD userRepositoryCRUD;
    EmailService emailService;
    PasswordService passwordService;
    PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepositoryCRUD userRepositoryCRUD,
                       EmailService emailService,
                       PasswordService passwordService,
                       PasswordEncoder passwordEncoder) {
        this.userRepositoryCRUD = userRepositoryCRUD;
        this.emailService = emailService;
        this.passwordService = passwordService;
        this.passwordEncoder = passwordEncoder;
    }

    public long getIdByEmail(String email) throws UserNotFoundException {
        Optional<User> user = userRepositoryCRUD.getUserByEmail(email);
        return user.orElseThrow(UserNotFoundException::new).getId();
    }

    public void addNewUser(String email, String password, String nick) throws EmailAlreadyRegistered, NickAlreadyRegistered, UserNotFoundException {
        if(userRepositoryCRUD.getUserByEmail(email).isPresent()){
            throw new EmailAlreadyRegistered();
        }
        if(userRepositoryCRUD.getUserByNick(nick).isPresent()){
            throw new NickAlreadyRegistered();
        }
        userRepositoryCRUD.save(User.builder().password(passwordEncoder.encode(password)).nick(nick).email(email).enabled(false).build());
        passwordService.generateUserCode(email);
    }

    public String getUserNick(String email) throws UserNotFoundException {
        Optional<User> user = userRepositoryCRUD.getUserByEmail(email);
        return user.orElseThrow(UserNotFoundException::new).getNick();
    }

    public List<UserDTO> getAllUsers(String nick, Integer page, Integer limit) throws UserNotFoundException {
        Optional<Page<User>> users = userRepositoryCRUD.findByNickContaining(nick, new PageRequest(page, limit));
        return users.orElseThrow(UserNotFoundException::new).getContent().stream()
                .map(user -> UserToDtoConverter.convert(user))
                .collect(Collectors.toList());
    }
}
