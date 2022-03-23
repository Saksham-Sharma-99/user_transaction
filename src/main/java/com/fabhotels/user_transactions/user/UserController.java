package com.fabhotels.user_transactions.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    private UserService userService;

    @PostMapping("/user/register")
    public Status registerUser(@RequestParam String name,@RequestParam String email,@RequestParam String password){
        return userService.registerUser(name,email,password);
    }

    @PostMapping("/user/login")
    public Status loginUser(@RequestParam String email, @RequestParam String name){
        return userService.loginUser(name,email);
    }

    @PostMapping("/user/logout")
    public Status logUserOut(@Validated @RequestBody Integer userId) {
        return userService.logOut(userId);
    }

    @GetMapping("/user")
    public HashMap<String,String> getUser(@Validated @RequestParam Integer id){
        return userService.getUser(id);
    }

    @PutMapping("/user")
    public Status updateUser(@RequestParam Integer userId , @RequestParam HashMap<String,String> data){
        return userService.updateUser(userId,data);
    }
}

