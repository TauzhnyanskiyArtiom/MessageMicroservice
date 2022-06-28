package ga.continent.service.decorator;

import ga.continent.api.dto.MessageCreateDto;
import ga.continent.api.dto.MessageReadDto;
import ga.continent.service.interfaces.MessageService;
import ga.continent.store.entity.MessageEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Slf4j
@Service
public class LoggedMessageService implements MessageService {

    MessageService messageServiceImpl;

    @Override
    public List<MessageReadDto> findForChannels(List<String> usersId) {
        log.info("Messages for users:");
        usersId.stream().peek(userId -> log.info("User id:", userId));
        return messageServiceImpl.findForChannels(usersId);
    }

    @Override
    public Optional<MessageReadDto> updateMessage(Long messageId, MessageCreateDto message) {
        log.info("Message id: " + messageId);
        log.info("Message new text: " + message.getText());

        return messageServiceImpl.updateMessage(messageId, message);

    }

    @Override
    public Optional<MessageReadDto> getMessageById(Long messageId) {
        log.info("Message id: " + messageId);
        return messageServiceImpl.getMessageById(messageId);
    }


    @Override
    public boolean deleteMessage(Long messageId) {
        log.info("Message id for delete:" + messageId);

        return messageServiceImpl.deleteMessage(messageId);
    }

    @Override
    public MessageReadDto createMessage(MessageCreateDto message) {
        log.info("Create message: ");
        log.info("User id: " + message.getAuthor().getId());
        log.info("Message text: " + message.getText());

        return messageServiceImpl.createMessage(message);
    }

    @Override
    public List<MessageReadDto> getListMessages(Optional<String> optionalPrefixName) {
        log.info("Search messages: " + optionalPrefixName.get());

        return messageServiceImpl.getListMessages(optionalPrefixName);
    }

    @Override
    public Optional<MessageEntity> findById(Long messageId) {
        log.info("Find Message by id: " + messageId);

        return messageServiceImpl.findById(messageId);
    }
}
