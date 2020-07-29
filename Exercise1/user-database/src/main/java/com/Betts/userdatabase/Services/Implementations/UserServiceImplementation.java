package com.Betts.userdatabase.Services.Implementations;

import com.Betts.userdatabase.Models.User;
import com.Betts.userdatabase.Services.UserService;
import com.Betts.userdatabase.dao.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;

    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public Optional<User> getUserByID(Long id) {
        Optional<User> returnValue = userRepository.findById(id);
        return returnValue;
    }

    @Override
    public Optional<User> getUserByEmailAddress(String emailAddress) {
        Optional<User> returnValue = userRepository.findByEmailAddress(emailAddress);
        return returnValue;
    }
}
