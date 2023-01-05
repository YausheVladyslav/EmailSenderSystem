package com.example.emailsender.controllers;

import com.example.emailsender.entities.UserEntity;
import com.example.emailsender.requests.AddEditUserRequest;
import com.example.emailsender.requests.EditEmailAndUserByEmailRequest;
import com.example.emailsender.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/add-user")
    public ResponseEntity<Void> addUser(@Valid @RequestBody AddEditUserRequest userRequest) {
        userService.saveUser(userRequest.getUsername(), userRequest.getEmail());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/edit-user")
    public ResponseEntity<Void> editUser(
            @RequestParam long userId,
            @Valid
            @RequestBody AddEditUserRequest userRequest
    ) {
        userService.editUser(userId, userRequest.getUsername(), userRequest.getEmail());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete-user")
    public ResponseEntity<Void> deleteUser(
            @Valid
            @RequestBody EditEmailAndUserByEmailRequest deleteRequest
    ) {
        userService.deleteUser(deleteRequest.getEmail());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get-user")
    public ResponseEntity<UserEntity> getUser(
            @Valid
            @RequestBody EditEmailAndUserByEmailRequest userRequest
    ) {
        return ResponseEntity.ok(userService.findUser(userRequest.getEmail()));
    }

    @GetMapping("/users-list")
    public ResponseEntity<Page<UserEntity>> usersList(@RequestParam int pageNumber) {
        return ResponseEntity.ok(userService.allUsers(pageNumber));
    }
}
