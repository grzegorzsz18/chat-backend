package pl.szczepaniak.chat.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "messages")
public class Message {

    @GeneratedValue
    @Id
    long id;
    String text;
    Long time;
    @ManyToOne
    @ElementCollection(targetClass=Conversation.class)
    Conversation conversation;
    @ManyToOne
    @ElementCollection(targetClass=User.class)
    User user;
    boolean isDisplayed;
}
