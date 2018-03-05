package pl.szczepaniak.chat.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.szczepaniak.chat.model.entity.Conversation;
import pl.szczepaniak.chat.model.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConversationRepositoryCRUD extends CrudRepository<Conversation, Long>{
    Optional<Page<Conversation>> findByUsersContains(User user, Pageable pageable);
    Optional<Conversation> findOneById(Long id);
}
