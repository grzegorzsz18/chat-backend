package pl.szczepaniak.chat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import pl.szczepaniak.chat.Utils.AppConfig;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import java.util.Properties;

@Service
public class EmailService {

    @Qualifier("getJavaMailSender")
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendConfirmLink(String email, String code){
        String text = "please confirm your email clicking in link " + "http://localhost:1818/password/user/" + email + "/" + code;
        String title = "chat app email confirmation";
        sendSimpleMessage(email, text, title);
    }

    private void sendSimpleMessage(String to, String text, String title){
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(title);
            message.setText(text);
            javaMailSender.send(message);
    };

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername(AppConfig.EMAIL);
        mailSender.setPassword(AppConfig.PASSWORD);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }
}
