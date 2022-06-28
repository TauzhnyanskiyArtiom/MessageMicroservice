package ga.continent.service.implementation;

import ga.continent.api.dto.UserReadDto;
import ga.continent.api.mapper.UserReadMapper;
import ga.continent.service.interfaces.UserService;
import ga.continent.store.entity.UserEntity;
import ga.continent.store.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    UserReadMapper userReadMapper;


    @Override
    public UserEntity getUser(UserReadDto userReadDto) {

        final UserEntity user = userRepository.findById(userReadDto.getId())
                .orElseGet(() -> userReadMapper.map(userReadDto));
        return create(user);
    }

    @Transactional
    @Override
    public UserEntity create(UserEntity user) {
        return userRepository.saveAndFlush(user);
    }
}
