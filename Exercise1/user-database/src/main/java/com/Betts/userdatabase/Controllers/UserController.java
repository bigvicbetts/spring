package com.Betts.userdatabase.Controllers;

import com.Betts.userdatabase.Dto.UserDto;
import com.Betts.userdatabase.Models.Request.UserRequest;
import com.Betts.userdatabase.Models.Response.UserResponse;
import com.Betts.userdatabase.Models.User;
import com.Betts.userdatabase.Services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserResponse> getUsers() {
        List<UserResponse> returnValue = new ArrayList<>();
        List<UserDto> userDtos = userService.getUsers();
        for (UserDto userDto : userDtos) {
            UserResponse temp = new UserResponse();
            BeanUtils.copyProperties(userDto, temp);
            returnValue.add(temp);
        }
        return returnValue;
    }

    @GetMapping(path = "/publicID/{publicID}")
    public UserResponse getUser(@PathVariable UUID publicID) {
        UserResponse returnValue = new UserResponse();
        UserDto userDto = userService.getUserByPublicID(publicID);
        BeanUtils.copyProperties(userDto, returnValue);
        return returnValue;
    }

    @GetMapping(path = "email/{emailAddress}")
    public UserResponse getUser(@PathVariable String emailAddress) {
        UserResponse returnValue = new UserResponse();
        UserDto userDto = userService.getUserByEmailAddress(emailAddress);
        BeanUtils.copyProperties(userDto, returnValue);
        return returnValue;
    }

    @PostMapping
    public UserResponse createUser(@RequestBody UserRequest userRequest) {
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userRequest, userDto);

        UserDto updatedUser = userService.createUser(userDto);

        UserResponse returnValue = new UserResponse();
        BeanUtils.copyProperties(updatedUser, returnValue);
        return returnValue;
    }

    @PutMapping(path = "/publicID/{publicID}")
    public UserResponse updateUser(@PathVariable UUID publicID, @RequestBody UserRequest userRequest) {
        UserResponse returnValue = new UserResponse();
        UserDto userDto = userService.updateUser(publicID, userRequest);
        BeanUtils.copyProperties(userDto, returnValue);
        return returnValue;
    }

    @DeleteMapping(path = "/publicID/{publicID}")
    public UserResponse deleteUser(@PathVariable UUID publicID) {
        UserResponse returnValue = new UserResponse();
        UserDto userDto = userService.deleteUser(publicID);
        BeanUtils.copyProperties(userDto, returnValue);
        return returnValue;
    }

    /*
    @GetMapping
    public List<User> getUsers() {
        List<User> returnValue = userService.getUsers();
        return returnValue;
    }

    @GetMapping(path = "/id/{id}")
    public Optional<User> getUser(@PathVariable Long id) {
        Optional<User> returnValue = userService.getUserByID(id);
        return returnValue;
    }

    @GetMapping(path="/email/{emailAddress}")
    public Optional<User> getUser(@PathVariable String emailAddress) {
        Optional<User> returnValue = userService.getUserByEmailAddress(emailAddress);
        return returnValue;
    }

    @PostMapping
    public void addUser(@RequestBody User user) {
        userService.addUser(user);
    }

    @PutMapping(path="/id/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        userService.updateUser(id, userDetails);
    }

    @DeleteMapping(path="/id/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
    */


}