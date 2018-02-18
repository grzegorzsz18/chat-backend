package pl.szczepaniak.chat.model;

import org.springframework.data.repository.CrudRepository;
import pl.szczepaniak.chat.model.entity.Conversation;

public interface ConversationRepositoryCRUD extends CrudRepository<Conversation, Long>{
}
