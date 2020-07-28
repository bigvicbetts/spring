package com.Betts.userdatabase.Services;

import com.Betts.userdatabase.Models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getUsers();

    void addUser(User user);

    Optional<User> getUser(Long id);
}
