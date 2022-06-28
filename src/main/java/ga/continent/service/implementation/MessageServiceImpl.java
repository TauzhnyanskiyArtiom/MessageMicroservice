package ga.continent.service.implementation;

import ga.continent.api.dto.EventType;
import ga.continent.api.dto.MessageCreateDto;
import ga.continent.api.dto.MessageReadDto;
import ga.continent.api.dto.ObjectType;
import ga.continent.api.mapper.MessageCreateMapper;
import ga.continent.api.mapper.MessageReadMapper;
import ga.continent.api.util.WsSender;
import ga.continent.service.interfaces.MessageService;
import ga.continent.service.interfaces.MetaContentService;
import ga.continent.store.entity.MessageEntity;
import ga.continent.store.repository.MessageRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Transactional(readOnly = true)
@Service
public class MessageServiceImpl implements MessageService {

    MetaContentService metaService;

    MessageReadMapper messageReadMapper;

    MessageCreateMapper messageCreateMapper;

    MessageRepository messageRepository;

    BiConsumer<EventType, MessageReadDto> wsSender;


    public MessageServiceImpl(MessageRepository messageRepository, MetaContentService metaService, MessageReadMapper messageReadMapper, MessageCreateMapper messageCreateMapper, WsSender wsSender) {
        this.messageRepository = messageRepository;
        this.metaService = metaService;
        this.messageReadMapper = messageReadMapper;
        this.messageCreateMapper = messageCreateMapper;
        this.wsSender = wsSender.getSender(ObjectType.MESSAGE);
    }

    @Override
    public List<MessageReadDto> findForChannels(List<String> usersId) {
        List<MessageEntity> messages = messageRepository.findByAuthorIdIn(usersId, Sort.by("id").descending());
        return messages.stream().map(messageReadMapper::map).collect(Collectors.toList());
    }


    @Transactional
    @Override
    public Optional<MessageReadDto> updateMessage(Long messageId, MessageCreateDto message) {

        return messageRepository.findById(messageId)
                .map(messageFromDb -> {
                    messageFromDb.setText(message.getText());
                    metaService.fillMeta(messageFromDb);
                    return messageRepository.saveAndFlush(messageFromDb);
                }).map(messageReadMapper::map)
                .map(messageReadDto -> {
                    wsSender.accept(EventType.UPDATE, messageReadDto);
                    return messageReadDto;
                });
    }

    @Override
    public Optional<MessageReadDto> getMessageById(Long messageId) {
        return messageRepository.findById(messageId).map(messageReadMapper::map);
    }


    @Transactional
    @Override
    public boolean deleteMessage(Long messageId) {
        return messageRepository.findById(messageId)
                .map(entity -> {
                    messageRepository.delete(entity);
                    messageRepository.flush();
                    MessageReadDto messageReadDto = messageReadMapper.map(entity);
                    wsSender.accept(EventType.REMOVE, messageReadDto);
                    return true;
                })
                .orElse(false);
    }

    @Transactional
    @Override
    public MessageReadDto createMessage(MessageCreateDto message) {
        return Optional.of(message)
                .map(messageCreateMapper::map)
                .map(ms -> {
                    metaService.fillMeta(ms);
                    return messageRepository.saveAndFlush(ms);
                })
                .map(messageReadMapper::map).orElse(null);
    }

    @Override
    public List<MessageReadDto> getListMessages(Optional<String> optionalPrefixName) {
        optionalPrefixName = optionalPrefixName.filter(prefixName -> !prefixName.trim().isEmpty());
        return optionalPrefixName
                .map(messageRepository::findAllByTextContainingIgnoreCase)
                .orElseGet(() -> messageRepository.findAll())
                .stream().map(messageReadMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<MessageEntity> findById(Long messageId) {
        return messageRepository.findById(messageId);
    }


}
