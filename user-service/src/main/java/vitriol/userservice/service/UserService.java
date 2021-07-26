package vitriol.userservice.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import vitriol.userservice.dto.UserDto;
import vitriol.userservice.jpa.UserEntity;

public interface UserService extends UserDetailsService {

    UserDto createUser(UserDto userDto);

    UserDto getUserByUserId(String userId);

    Iterable<UserEntity> getUserByAll();

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    UserDto getUserDetailsByEmail(String userName);
}
