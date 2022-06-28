package ga.continent.api.controller;

import ga.continent.api.dto.CommentCreateDto;
import ga.continent.api.dto.CommentReadDto;
import ga.continent.service.interfaces.CommentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CommentController {

    CommentService loggedCommentService;

    @PostMapping
    public CommentReadDto createComment(
            @RequestBody CommentCreateDto comment
    ) {
        return loggedCommentService.create(comment);
    }

    @DeleteMapping("{comment_id}")
    public boolean deleteComment(@PathVariable("comment_id") Long commentId) {
        return loggedCommentService.deleteMessage(commentId);
    }
}
