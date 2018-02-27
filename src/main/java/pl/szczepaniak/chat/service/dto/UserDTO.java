package pl.szczepaniak.chat.service.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    String nick;
    String email;
}
