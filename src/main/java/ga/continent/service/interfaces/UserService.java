package ga.continent.service.interfaces;

import ga.continent.api.dto.UserReadDto;
import ga.continent.store.entity.UserEntity;

public interface UserService {

    UserEntity getUser(UserReadDto userReadDto);

    UserEntity create(UserEntity user);

}
