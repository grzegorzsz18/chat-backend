package pl.szczepaniak.chat.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "conversations")
public class Conversation {

    @Id
    @GeneratedValue
    long id;
    @OneToMany
    @ElementCollection(targetClass=User.class)
    Set<User> users;

}
