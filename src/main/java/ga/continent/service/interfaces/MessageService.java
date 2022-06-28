package ga.continent.service.interfaces;

import ga.continent.api.dto.MessageCreateDto;
import ga.continent.api.dto.MessageReadDto;
import ga.continent.store.entity.MessageEntity;

import java.util.List;
import java.util.Optional;

public interface MessageService {
    List<MessageReadDto> findForChannels(List<String> usersId);

    Optional<MessageReadDto> updateMessage(Long messageId, MessageCreateDto message);

    Optional<MessageReadDto> getMessageById(Long messageId);

    boolean deleteMessage(Long messageId);

    MessageReadDto createMessage(MessageCreateDto message);

    List<MessageReadDto> getListMessages(Optional<String> optionalPrefixName);

    Optional<MessageEntity> findById(Long messageId);

}
