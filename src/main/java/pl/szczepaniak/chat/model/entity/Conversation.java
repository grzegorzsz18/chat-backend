package pl.szczepaniak.chat.model.entity;

import lombok.*;

import javax.persistence.*;

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
    @ManyToOne
    @ElementCollection(targetClass=User.class)
    User userFirst;
    @ManyToOne
    @ElementCollection(targetClass=User.class)
    User userSecond;

}
