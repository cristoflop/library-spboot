package es.urjc.cloudapps.library.presentation;

import es.urjc.cloudapps.library.application.UserService;
import es.urjc.cloudapps.library.application.dtos.CreateUserDto;
import es.urjc.cloudapps.library.application.dtos.UpdateUserDto;
import es.urjc.cloudapps.library.application.dtos.UserCommentDto;
import es.urjc.cloudapps.library.application.dtos.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@Tag(name = "Users")
@RestController
@RequestMapping("/api")
public class RestUserController {

    private final UserService userService;

    public RestUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    @Operation(summary = "Get all the users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return all the users",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDto.class))})})
    public ResponseEntity<List<UserDto>> getUsers() {
        return ResponseEntity.ok(this.userService.getUsers());
    }

    @PostMapping("/users")
    @Operation(summary = "Create a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "The user has been created", headers = {
                    @Header(name = "Location", description = "URL where the new resource is located")
            }),
            @ApiResponse(responseCode = "400", description = "Fields input validation error"),
            @ApiResponse(responseCode = "409", description = "User already exists")
    })
    public ResponseEntity<Void> createUser(@RequestBody CreateUserDto userDto) {
        Long userId = this.userService.createUser(userDto);

        URI newUserLocation = fromCurrentRequest()
                .path("/{userId}")
                .buildAndExpand(userId)
                .toUri();

        return ResponseEntity
                .created(newUserLocation)
                .build();
    }

    @GetMapping("/users/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the user",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDto.class))}),
            @ApiResponse(responseCode = "404", content = {@Content()}, description = "User not found")})
    public UserDto getUser(@PathVariable Long id) {
        return this.userService.getUser(id);
    }

    @GetMapping("/users/{id}/comments")
    @Operation(summary = "Get all comments of this user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return the comments",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserCommentDto.class))})})
    public List<UserCommentDto> getUserComments(@PathVariable Long id) {
        return this.userService.getUserComments(id);
    }

    @DeleteMapping("/users/{id}")
    @Operation(summary = "Delete a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "The user has been deleted"),
            @ApiResponse(responseCode = "404", description = "User not found")})
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        this.userService.deleteUser(id);
        return ResponseEntity
                .noContent()
                .build();
    }

    @PatchMapping("/users/{id}")
    @Operation(summary = "Update a user email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "The email has been updated"),
            @ApiResponse(responseCode = "404", description = "User not found")})
    public ResponseEntity<Void> updateUserEmail(@RequestBody UpdateUserDto userDto, @PathVariable Long id) {
        userDto.setId(id);
        this.userService.updateUserEmail(userDto);
        return ResponseEntity
                .noContent()
                .build();
    }

}
