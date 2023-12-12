package com.store.management.tool.controller;

import com.store.management.tool.dto.UserDto;
import com.store.management.tool.model.User;
import com.store.management.tool.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("store/api/v1")
public class UserController {

    private final UserService userService;

    @PostMapping("/user/add")
    public ResponseEntity<UserDto> addUser(@RequestBody final UserDto userDto) {
        var response = userService.add(userDto);

        return ResponseEntity
                .ok()
                .body(response);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> retrieveUserById(@PathVariable final Integer id) {
        var response = userService.getById(id);

        return ResponseEntity
                .ok()
                .body(response);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> retrieveUsers() {
        var response = userService.getAll();

        return ResponseEntity
                .ok()
                .body(response);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<UserDto> updateUser(@RequestBody final UserDto userDto, @PathVariable final Integer id) {
        userService.update(userDto, id);

        return ResponseEntity
                .ok()
                .body(userDto);
    }

    @DeleteMapping("user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable final Integer id) {
        userService.deleteById(id);

        return ResponseEntity
                .ok()
                .build();
    }
}
