package ga.continent.api.mapper;

import ga.continent.api.dto.CommentReadDto;
import ga.continent.api.dto.UserReadDto;
import ga.continent.store.entity.CommentEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class CommentReadMapper implements Mapper<CommentEntity, CommentReadDto> {

    UserReadMapper userReadMapper;

    @Override
    public CommentReadDto map(CommentEntity object) {

        UserReadDto author = userReadMapper.mapTo(object.getAuthor());

        return CommentReadDto.builder()
                .id(object.getId())
                .message(object.getMessage().getId())
                .text(object.getText())
                .author(author)
                .build();
    }
}
