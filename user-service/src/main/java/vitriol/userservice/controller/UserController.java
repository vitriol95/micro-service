package vitriol.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vitriol.userservice.dto.UserDto;
import vitriol.userservice.service.UserService;
import vitriol.userservice.vo.Greeting;
import vitriol.userservice.vo.RequestUser;
import vitriol.userservice.vo.ResponseUser;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class UserController {

    private final MessageSource messageSource;
    private final UserService userService;
    private final ModelMapper mapper;
    private final Greeting greeting;

    @GetMapping("/health_check")
    public String status() {
        return "It's working in User Service";
    }

    @GetMapping("/welcome")
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
}
