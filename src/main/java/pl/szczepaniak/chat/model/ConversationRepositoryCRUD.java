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

import javax.jws.soap.SOAPBinding;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface ConversationRepositoryCRUD extends CrudRepository<Conversation, Long>{
    Optional<Page<Conversation>> findByUsersContains(User user, Pageable pageable);
    Optional<List<Conversation>> findByUsersContains(User user);
    Optional<Conversation> findOneById(Long id);
    Optional<List<Conversation>> findByUsersContainsAndUsersContains(User user1, User user2);
}
