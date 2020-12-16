package es.urjc.cloudapps.library.presentation;

import es.urjc.cloudapps.library.application.UserService;
import es.urjc.cloudapps.library.application.dtos.CreateUserDto;
import es.urjc.cloudapps.library.application.dtos.UserDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Users")
@RestController
@RequestMapping("/api")
public class RestUserController {

    private UserService userService;

    public RestUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<UserDto> getUsers() {
        return this.userService.getUsers();
    }

    @PostMapping("/users")
    public Long createUser(@RequestBody CreateUserDto userDto) {
        return this.userService.createUser(userDto);
    }

}