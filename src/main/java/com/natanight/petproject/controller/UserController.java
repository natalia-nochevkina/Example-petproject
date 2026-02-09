package com.natanight.petproject.controller;

import com.natanight.petproject.dto.user.CreateUserRequest;
import com.natanight.petproject.dto.user.UpdateUserRequest;
import com.natanight.petproject.dto.user.UserResponse;
import com.natanight.petproject.models.Message;
import com.natanight.petproject.models.User;
import com.natanight.petproject.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse addUser(@RequestBody @Valid CreateUserRequest request) {
        User user = userService.addUser(
                request.getUsername(),
                request.getPassword(),
                request.getEmail()
        );
        return UserResponse.fromEntity(user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Message deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new Message("deleted user with id = " + id.toString());
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse updateUser(
            @PathVariable Long id,
            @RequestBody @Valid UpdateUserRequest request
    ) {
        User user = userService.updateUser(
                id,
                request.getUsername(),
                request.getEmail()
        );
        return UserResponse.fromEntity(user);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return UserResponse.fromEntity(user);
    }

    @GetMapping("/get-by-username/{username}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse getByUsername(@PathVariable String username) {
        User user = userService.getUserByUsername(username);
        return UserResponse.fromEntity(user);
    }

    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponse> getAllUsers() {
        return userService
                .getAllUsers()
                .stream()
                .map(UserResponse::fromEntity)
                .toList();
    }

    @GetMapping("/get-users-by-filter")
    @ResponseStatus(HttpStatus.OK)
    public void getUsersByFilter() {
        // TODO: later
    }

    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    public User getLoggedUser() {
        // TODO: later
        return userService.getUserByUsername("nata");
    }
}
