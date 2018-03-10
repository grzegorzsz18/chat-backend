package pl.szczepaniak.chat.service.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO {
    String text;
    Long time;
    Boolean isDisplayed;
    String autor;
}
