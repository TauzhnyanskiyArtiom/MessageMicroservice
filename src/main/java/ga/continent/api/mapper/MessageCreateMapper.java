package ga.continent.api.mapper;

import ga.continent.api.dto.MessageCreateDto;
import ga.continent.service.interfaces.UserService;
import ga.continent.store.entity.MessageEntity;
import ga.continent.store.entity.UserEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class MessageCreateMapper implements Mapper<MessageCreateDto, MessageEntity> {

    UserService loggedUserService;

    @Override
    public MessageEntity map(MessageCreateDto object) {

        UserEntity author = loggedUserService.getUser(object.getAuthor());

        return MessageEntity.builder()
                .text(object.getText())
                .author(author)
                .build();
    }

}
