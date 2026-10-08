package com.app.ecom.controller;

import com.app.ecom.model.User;
import com.app.ecom.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        return new ResponseEntity<>(userService.fetchAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id){
            return userService.fetchUser(id)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody User user){
        userService.addUser(user);
        return ResponseEntity.ok("User added Successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatedUser(@PathVariable Long id, @RequestBody User updatedUser) {
        boolean updated = userService.updatedUser(id, updatedUser);
        if (updated)
            return ResponseEntity.ok("User updated Successfully");
             return ResponseEntity.notFound().build();
    }
}
