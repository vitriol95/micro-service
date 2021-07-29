package vitriol.userservice.controller;

import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vitriol.userservice.dto.UserDto;
import vitriol.userservice.jpa.UserEntity;
import vitriol.userservice.service.UserService;
import vitriol.userservice.vo.RequestUser;
import vitriol.userservice.vo.ResponseUser;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor // http://127.0.0.1:60000/welcome 식으로
public class UserController {

    private final MessageSource messageSource;
    private final UserService userService;
    private final ModelMapper mapper;
    private final Environment env;
//    private final Greeting greeting;

    @GetMapping("/health_check")
    @Timed(value = "users.status", longTask = true)
    public String status() {
        return String.format("It's working in User Service" + ", port(local.server.port)=" + env.getProperty("local.server.port")
                + ", port(server.port)=" + env.getProperty("server.port")
                + ", token secret= " + env.getProperty("token.secret")
                + ", token expiration time= " + env.getProperty("token.expiration_time"));
    }

    @GetMapping("/welcome")
    @Timed(value = "users.welcome", longTask = true)
    public String welcome() {
//        return greeting.getMessage();
        return messageSource.getMessage("greeting.message", null, null); // 두가지 방법 존재
    }

    @PostMapping("/users")
    public ResponseEntity<ResponseUser> createUser(@RequestBody RequestUser user) {
        UserDto userDto = mapper.map(user, UserDto.class);
        userService.createUser(userDto);
        ResponseUser responseUser = mapper.map(userDto, ResponseUser.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseUser);
    }

    @GetMapping("/users")
    public ResponseEntity<List<ResponseUser>> getUsers() {

        Iterable<UserEntity> userList = userService.getUserByAll();
        List<ResponseUser> result = new ArrayList<>();

        userList.forEach(userEntity -> {
            result.add(mapper.map(userEntity, ResponseUser.class));
        });

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<ResponseUser> getUser(@PathVariable("userId") String userId) {
        UserDto userDto = userService.getUserByUserId(userId);

        ResponseUser returnValue = mapper.map(userDto, ResponseUser.class);

        return ResponseEntity.status(HttpStatus.OK).body(returnValue);
    }
}
