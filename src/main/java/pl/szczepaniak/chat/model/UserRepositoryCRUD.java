package pl.szczepaniak.chat.model;

import org.springframework.data.repository.CrudRepository;
import pl.szczepaniak.chat.model.entity.User;


public interface UserRepositoryCRUD extends CrudRepository<User, Long> {

}
