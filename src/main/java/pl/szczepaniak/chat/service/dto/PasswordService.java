package pl.szczepaniak.chat.service.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.szczepaniak.chat.Utils.AppConfig;
import pl.szczepaniak.chat.exceptions.UserNotFoundException;
import pl.szczepaniak.chat.model.UserRepositoryCRUD;
import pl.szczepaniak.chat.model.entity.User;
import pl.szczepaniak.chat.service.EmailService;

import java.util.Optional;
import java.util.Random;

@Service
public class PasswordService {

    @Autowired
    UserRepositoryCRUD userRepositoryCRUD;
    @Autowired
    EmailService emailService;

    public String confirmPassword(String code, String email){
        Optional<User> user = userRepositoryCRUD.getUserByEmail(email);
        if(user.isPresent()) {
            //todo enable user
            return "Successfully confirmed password" + code + "  " + email;
        }
        return "Something goes wrong" + code + "   " + email;
    }

    public void generateUserCode(String email) throws UserNotFoundException {
        Optional<User> user = userRepositoryCRUD.getUserByEmail(email);
        if(user.isPresent()) {
            User u = user.get();
            Random randomGend = new Random();
            StringBuilder code = new StringBuilder();
            for(int i = 0; i < AppConfig.CODE_LENGHT; i++){
                code.append(randomGend.nextInt(9));
            }
            u.setCode(code.toString());
            userRepositoryCRUD.save(u);
            emailService.sendConfirmLink(email, code.toString());
        }
        else {
            throw new UserNotFoundException();
        }
    }
}
