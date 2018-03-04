package pl.szczepaniak.chat.service.dto;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConversationDTO {
    long id;
    List<UserDTO> users;
}
