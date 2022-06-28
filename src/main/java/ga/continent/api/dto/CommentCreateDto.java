package ga.continent.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommentCreateDto {
    String text;
    Long messageId;
    UserReadDto author;
}
