package ga.continent.api.controller;

import ga.continent.api.dto.ChannelsDto;
import ga.continent.api.dto.MessageCreateDto;
import ga.continent.api.dto.MessageReadDto;
import ga.continent.service.interfaces.MessageService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/messages")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class MessageController {

    MessageService loggedMessageService;


    @PostMapping("/channels")
    public List<MessageReadDto> findForChannels(@RequestBody ChannelsDto channelsDto) {

        return loggedMessageService.findForChannels(channelsDto.getChannelsId());
    }

    @GetMapping("{message_id}")
    public Optional<MessageReadDto> getOne(
            @PathVariable("message_id") Long messageId) {

        return loggedMessageService.getMessageById(messageId);
    }


    @PostMapping
    public MessageReadDto addMessage(
            @RequestBody MessageCreateDto message) {

        return loggedMessageService.createMessage(message);
    }

    @PutMapping("{message_id}")
    public MessageReadDto updateMessage(
            @PathVariable("message_id") Long messageId,
            @RequestBody MessageCreateDto message) {

        return loggedMessageService
                .updateMessage(messageId, message)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("{message_id}")
    public boolean deleteMessage(@PathVariable("message_id") Long messageId) {
        return loggedMessageService.deleteMessage(messageId);
    }

}
