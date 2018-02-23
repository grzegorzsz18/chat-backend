package pl.szczepaniak.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.szczepaniak.chat.exceptions.EmailAlreadyRegistered;
import pl.szczepaniak.chat.exceptions.NickAlreadyRegistered;
import pl.szczepaniak.chat.exceptions.UserNotFoundException;
import pl.szczepaniak.chat.model.UserRepositoryCRUD;
import pl.szczepaniak.chat.model.entity.User;
import pl.szczepaniak.chat.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/id")
    public long getUserId(@RequestParam(value = "email", required = false) String email) throws UserNotFoundException {
        return userService.getIdByEmail(email);
    }

    @PutMapping("/new")
    public ResponseEntity addNewUser(@RequestParam(value = "email") String email,
                           @RequestParam(value = "password") String password,
                           @RequestParam(value = "nick") String nick) throws EmailAlreadyRegistered, NickAlreadyRegistered {
        userService.addNewUser(email, password, nick);
        return new ResponseEntity(HttpStatus.CREATED);
    }

}