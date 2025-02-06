package com.example.demo.Controller;

import com.example.demo.DTO.UserUpdateFields;
import com.example.demo.Entity.User;
import com.example.demo.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String registerUser(@Valid @RequestBody User user) {

        return userService.saveUser(user);
    }

    @PutMapping("/update")
    public String updateUser(@Valid @RequestBody UserUpdateFields user, @RequestHeader("Authorization") String token) {

        return userService.updateUser(user,token);
    }

}
