package pl.szczepaniak.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.szczepaniak.chat.service.PasswordService;

@RestController
@RequestMapping("/password")
public class PasswordController {

    @Autowired
    PasswordService passwordService;

    @GetMapping("/user/{email}/{code}")
    public String confirmPassword(@PathVariable("code") String code, @PathVariable("email") String email){
        return passwordService.confirmPassword(code, email);
    }
}
