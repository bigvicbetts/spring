package com.Betts.userdatabase.Services;

import com.Betts.userdatabase.Dto.UserDto;
import com.Betts.userdatabase.Models.Request.UserRequest;
import com.Betts.userdatabase.Models.Response.UserResponse;
import com.Betts.userdatabase.Models.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserDto createUser(UserDto userDto);

    List<UserDto> getUsers(Integer pageNo, Integer pageSize, String id);

    UserDto getUserByPublicID(UUID id);

    UserDto getUserByEmailAddress(String emailAddress);

    UserDto updateUser(UUID publicID, UserRequest userRequest);

    UserDto deleteUser(UUID publicID);

    /*
    List<User> getUsers();

    void addUser(User user);

    Optional<User> getUserByID(Long id);

    Optional<User> getUserByEmailAddress(String emailAddress);

    void updateUser(Long id, User userDetails);

    void deleteUser(Long id);

     */
}
