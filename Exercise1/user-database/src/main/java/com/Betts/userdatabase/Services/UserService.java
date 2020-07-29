package com.Betts.userdatabase.Services;

import com.Betts.userdatabase.Models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getUsers();

    void addUser(User user);

    Optional<User> getUserByID(Long id);

    Optional<User> getUserByEmailAddress(String emailAddress);
}
