package org.anotherone.controllers;

import lombok.RequiredArgsConstructor;
import org.anotherone.dto.CreateUserRequest;
import org.anotherone.dto.UsersResponse;
import org.anotherone.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserControllers {

    private final UserService userService;

    @GetMapping(value = "/all", produces = APPLICATION_JSON_VALUE)
    public List<UsersResponse> findAllUsers() {
        return userService.findAll();
    }

    @GetMapping(value = "/{userId}", produces = APPLICATION_JSON_VALUE)
    public UsersResponse findUserById(@PathVariable Long userId) {
        return userService.findById(userId);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public UsersResponse create(@RequestBody CreateUserRequest createUserRequest) {
        return userService.createUser(createUserRequest);
    }

    @DeleteMapping(value = "/{userId}", produces = APPLICATION_JSON_VALUE)
    public void deleteUserById(@PathVariable Long userId) {
        userService.delete(userId);
    }
}
