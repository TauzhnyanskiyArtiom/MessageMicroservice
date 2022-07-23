package ga.continent.service.listener;

import com.google.gson.Gson;
import ga.continent.api.dto.UserReadDto;
import ga.continent.api.mapper.UserReadMapper;
import ga.continent.service.interfaces.UserService;
import ga.continent.store.entity.UserEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class UserListener {

    Gson gson;
    UserReadMapper userReadMapper;
    UserService loggedUserService;

    @KafkaListener(id = "UserGroupId", topics = "UserInfo")
    public void kafkaListen(String data) {
        UserReadDto userReadDto = gson.fromJson(data, UserReadDto.class);
        UserEntity user = userReadMapper.map(userReadDto);
        loggedUserService.create(user);
    }
}
