package ga.continent.service.decorator;

import ga.continent.api.dto.UserReadDto;
import ga.continent.service.interfaces.UserService;
import ga.continent.store.entity.UserEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Slf4j
@Service
public class LoggedUserService implements UserService {

    UserService userServiceImpl;

    @Override
    public UserEntity getUser(UserReadDto userReadDto) {
        log.info("Get user by id: " + userReadDto.getId());
        return userServiceImpl.getUser(userReadDto);
    }

    @Override
    public UserEntity create(UserEntity user) {
        log.info("Save user: " + user.getName() + " id : " + user.getId());
        return userServiceImpl.create(user);
    }

}
