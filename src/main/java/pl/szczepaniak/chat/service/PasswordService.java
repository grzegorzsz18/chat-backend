package pl.szczepaniak.chat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.szczepaniak.chat.Utils.AppConfig;
import pl.szczepaniak.chat.exceptions.UserNotFoundException;
import pl.szczepaniak.chat.model.UserRepositoryCRUD;
import pl.szczepaniak.chat.model.entity.User;

import java.util.Optional;
import java.util.Random;

@Service
public class PasswordService {

    UserRepositoryCRUD userRepositoryCRUD;
    EmailService emailService;

    @Autowired
    public PasswordService(UserRepositoryCRUD userRepositoryCRUD, EmailService emailService) {
        this.userRepositoryCRUD = userRepositoryCRUD;
        this.emailService = emailService;
    }

    public String confirmPassword(String code, String email) {
        Optional<User> user = userRepositoryCRUD.getUserByEmail(email);
        if(user.isPresent() && user.get().getCode().equals(code)) {
            User u = user.get();
            u.setEnabled(true);
            userRepositoryCRUD.save(u);
            return "Successfully confirmed password. Your can now log into our app. Enjoy!";
        }
        return "Something goes wrong. Please check your link.";
    }

    public void generateUserCode(String email) throws UserNotFoundException {
        User user = userRepositoryCRUD.getUserByEmail(email)
                .orElseThrow(UserNotFoundException::new);
        Random randomGend = new Random();
        StringBuilder code = new StringBuilder();
        for(int i = 0; i < AppConfig.CODE_LENGHT; i++){
            code.append(randomGend.nextInt(9));
        }
        user.setCode(code.toString());
        userRepositoryCRUD.save(user);
        emailService.sendConfirmLink(email, code.toString());
        }
}
