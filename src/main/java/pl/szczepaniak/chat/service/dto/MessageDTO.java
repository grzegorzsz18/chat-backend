package pl.szczepaniak.chat.service.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO {
    Long conversationId;
    String text;
    Long time;
    Boolean isDisplayed;
    String autor;
}
