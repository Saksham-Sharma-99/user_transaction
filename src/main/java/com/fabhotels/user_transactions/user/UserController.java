package com.fabhotels.user_transactions.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    private UserService userService;

    @PostMapping("/user/register")
    public Status registerUser(@Validated @RequestBody User newUser){
        return userService.registerUser(newUser);
    }

    @PostMapping("/user/login")
    public Status loginUser(@Validated @RequestBody User user){
        return userService.loginUser(user);
    }

    @PostMapping("/user/logout")
    public Status logUserOut(@Validated @RequestBody User user) {
        return userService.logOut(user);
    }

    @GetMapping("/user")
    public Optional<User> getUser(@Validated @RequestParam Integer id){
        return userService.getUser(id);
    }

    @PutMapping("/user")
    public Status updateUser(@Validated @RequestBody User user){
        return userService.updateUser(user);
    }
}

