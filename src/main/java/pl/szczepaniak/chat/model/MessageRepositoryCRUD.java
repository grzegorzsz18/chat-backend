package pl.szczepaniak.chat.model;

import org.springframework.data.repository.CrudRepository;
import pl.szczepaniak.chat.model.entity.Message;

public interface MessageRepositoryCRUD extends CrudRepository<Message, Long>{
}
