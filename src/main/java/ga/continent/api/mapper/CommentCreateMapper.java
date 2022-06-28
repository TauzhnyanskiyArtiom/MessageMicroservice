package ga.continent.api.mapper;

import ga.continent.api.dto.CommentCreateDto;
import ga.continent.service.interfaces.MessageService;
import ga.continent.service.interfaces.UserService;
import ga.continent.store.entity.CommentEntity;
import ga.continent.store.entity.MessageEntity;
import ga.continent.store.entity.UserEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class CommentCreateMapper implements Mapper<CommentCreateDto, CommentEntity> {

    UserService loggedUserService;

    MessageService loggedMessageService;

    @Override
    public CommentEntity map(CommentCreateDto object) {

        UserEntity author = loggedUserService.getUser(object.getAuthor());

        return CommentEntity.builder()
                .text(object.getText())
                .message(getMessage(object.getMessageId()))
                .author(author)
                .build();
    }

    private MessageEntity getMessage(Long messageId) {
        return Optional.of(messageId)
                .flatMap(loggedMessageService::findById).orElse(null);
    }
}
