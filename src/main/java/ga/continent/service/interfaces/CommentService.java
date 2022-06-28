package ga.continent.service.interfaces;

import ga.continent.api.dto.CommentCreateDto;
import ga.continent.api.dto.CommentReadDto;

public interface CommentService {
    CommentReadDto create(CommentCreateDto comment);

    boolean deleteMessage(Long commentId);
}
