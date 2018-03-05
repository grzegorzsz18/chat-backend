package pl.szczepaniak.chat.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import pl.szczepaniak.chat.model.entity.Conversation;
import pl.szczepaniak.chat.model.entity.Message;

import java.util.Optional;

public interface MessageRepositoryCRUD extends CrudRepository<Message, Long>{

    Optional<Page<Message>> getAllByConversation(Conversation conversation, Pageable pageable);
}
