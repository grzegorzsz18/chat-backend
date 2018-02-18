package pl.szczepaniak.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.szczepaniak.chat.model.UserRepositoryCRUD;
import pl.szczepaniak.chat.model.entity.User;

@RestController
@RequestMapping("ok")
public class Controller {

    @Autowired
    UserRepositoryCRUD userRepositoryCRUD;

    @GetMapping("/ok")
    public void test(){
        userRepositoryCRUD.save(User.builder().nick("nick").password("pass").build());
        System.err.print("okokkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
    }

}