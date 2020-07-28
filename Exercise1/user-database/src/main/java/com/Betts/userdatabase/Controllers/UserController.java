package com.Betts.userdatabase.Controllers;

import com.Betts.userdatabase.Models.User;
import com.Betts.userdatabase.Services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers() {
        List<User> returnValue = userService.getUsers();
        return returnValue;
    }

    @GetMapping(path = "/{id}")
    public Optional<User> getUser(@PathVariable Long id) {
        Optional<User> returnValue = userService.getUser(id);
        return returnValue;
    }

    @PostMapping
    public void addUser(@RequestBody User user) {
        userService.addUser(user);
    }
}
