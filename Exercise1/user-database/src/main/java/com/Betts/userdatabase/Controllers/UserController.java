package com.Betts.userdatabase.Controllers;

import com.Betts.userdatabase.Dto.UserDto;
import com.Betts.userdatabase.Exceptions.CustomAppException;
import com.Betts.userdatabase.Models.Request.UserRequest;
import com.Betts.userdatabase.Models.Response.UserResponse;
import com.Betts.userdatabase.Services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserResponse> getUsers(@RequestParam(defaultValue = "0") Integer pageNo,
                                       @RequestParam(defaultValue = "5") Integer pageSize,
                                       @RequestParam(defaultValue = "id") String sortBy) {
        List<UserResponse> returnValue = new ArrayList<>();
        List<UserDto> userDtos = userService.getUsers(pageNo, pageSize, sortBy);
        for (UserDto userDto : userDtos) {
            UserResponse temp = new UserResponse();
            BeanUtils.copyProperties(userDto, temp);
            returnValue.add(temp);
        }
        return returnValue;
    }

    @GetMapping(path = "/publicID/{publicID}")
    public UserResponse getUser(@PathVariable UUID publicID) throws CustomAppException {
        try {
            UserResponse returnValue = new UserResponse();
            UserDto userDto = userService.getUserByPublicID(publicID);
            BeanUtils.copyProperties(userDto, returnValue);
            return returnValue;
        }
        catch(NoSuchElementException exc) {
            throw new CustomAppException("That ID is not present in the database");
        }
    }

    @GetMapping(path = "email/{emailAddress}")
    public UserResponse getUser(@PathVariable String emailAddress) throws CustomAppException {
        try {
            UserResponse returnValue = new UserResponse();
            UserDto userDto = userService.getUserByEmailAddress(emailAddress);
            BeanUtils.copyProperties(userDto, returnValue);
            return returnValue;
        }
        catch(NoSuchElementException exc) {
            throw new CustomAppException("That email address is not present in the database");
        }
    }

    @PostMapping
    public UserResponse createUser(@RequestBody UserRequest userRequest) throws CustomAppException {
        try {
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(userRequest, userDto);

            UserDto updatedUser = userService.createUser(userDto);

            UserResponse returnValue = new UserResponse();
            BeanUtils.copyProperties(updatedUser, returnValue);
            return returnValue;
        }
        catch(NoSuchElementException exc) {
            throw new CustomAppException("That ID is not present in the database");
        }
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
}