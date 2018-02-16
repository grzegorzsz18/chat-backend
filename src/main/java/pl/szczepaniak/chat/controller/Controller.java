package pl.szczepaniak.chat.controller;

import lombok.Lombok;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.szczepaniak.chat.model.UserCRUD;
import pl.szczepaniak.chat.model.entity.User;

@RestController
@RequestMapping("ok")
public class Controller {

    @Autowired
    UserCRUD userCRUD;

    @GetMapping("/ok")
    public void test(){
        userCRUD.save(User.builder().nick("nick").password("pass").build());
        System.err.print("okokkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
    }

}