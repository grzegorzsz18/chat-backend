package pl.szczepaniak.chat.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import pl.szczepaniak.chat.model.entity.User;

import java.util.ArrayList;
import java.util.Optional;


public interface UserRepositoryCRUD extends CrudRepository<User, Long> {

    Optional<User> getUserByEmail(String email);
    Optional<User> getUserByNick(String nick);
    Optional<Page<User>> findByNickContaining(String nick, Pageable pageable);
}
