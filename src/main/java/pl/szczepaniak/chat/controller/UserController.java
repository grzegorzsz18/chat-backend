package pl.szczepaniak.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.szczepaniak.chat.exceptions.EmailAlreadyRegistered;
import pl.szczepaniak.chat.exceptions.NickAlreadyRegistered;
import pl.szczepaniak.chat.exceptions.UserNotFoundException;
import pl.szczepaniak.chat.service.PhotoService;
import pl.szczepaniak.chat.service.UserService;
import pl.szczepaniak.chat.service.dto.UserDTO;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    PhotoService photoService;
    UserService userService;

    @Autowired
    public UserController(PhotoService photoService, UserService userService) {
        this.photoService = photoService;
        this.userService = userService;
    }

    @GetMapping("/id")
    public long getUserId(@RequestParam(value = "email", required = false) String email) throws UserNotFoundException {
        return userService.getIdByEmail(email);
    }

    @GetMapping("/nick")
    public ResponseEntity<String> getUserNick(@RequestParam(value = "email") String email) throws UserNotFoundException {
        return new ResponseEntity<>(userService.getUserNick(email),HttpStatus.OK);
    }

    @PutMapping("/new")
    public ResponseEntity addNewUser(@RequestParam(value = "email") String email,
                           @RequestParam(value = "password") String password,
                           @RequestParam(value = "nick") String nick) throws EmailAlreadyRegistered, NickAlreadyRegistered {
        userService.addNewUser(email, password, nick);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                                     @RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit,
                                                     @RequestParam(value = "nick", required = false, defaultValue = "") String nick) throws UserNotFoundException {
        return new ResponseEntity<>(userService.getAllUsers(nick, page, limit), HttpStatus.OK);
    }


}