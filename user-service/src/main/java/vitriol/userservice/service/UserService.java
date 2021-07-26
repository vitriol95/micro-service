package vitriol.userservice.service;

import vitriol.userservice.dto.UserDto;
import vitriol.userservice.jpa.UserEntity;

public interface UserService {

    UserDto createUser(UserDto userDto);

    UserDto getUserByUserId(String userId);

    Iterable<UserEntity> getUserByAll();
}
