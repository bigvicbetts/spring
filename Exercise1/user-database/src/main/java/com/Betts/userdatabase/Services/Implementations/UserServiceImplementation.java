package com.Betts.userdatabase.Services.Implementations;

import com.Betts.userdatabase.Dto.UserDto;
import com.Betts.userdatabase.Models.Request.UserRequest;
import com.Betts.userdatabase.Models.Response.UserResponse;
import com.Betts.userdatabase.Models.User;
import com.Betts.userdatabase.Services.UserService;
import com.Betts.userdatabase.dao.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;

    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDto> getUsers(Integer pageNo, Integer pageSize, String id) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(id));
        Page<User> users = userRepository.findAll(paging);
        List<UserDto> userDto = new ArrayList<>();
        for (User user : users) {
            UserDto temp = new UserDto();
            temp.setHttpStatus(HttpStatus.ACCEPTED);
            BeanUtils.copyProperties(user, temp);
            userDto.add(temp);
        }
        return userDto;
    }

    @Override
    public UserDto getUserByPublicID(UUID publicID) {
        User selectedUser = userRepository.findByPublicID(publicID).get();
        UserDto returnValue = new UserDto();
        returnValue.setHttpStatus(HttpStatus.ACCEPTED);
        BeanUtils.copyProperties(selectedUser, returnValue);
        return returnValue;
    }

    @Override
    public UserDto getUserByEmailAddress(String emailAddress) {
        User selectedUser = userRepository.findByEmailAddress(emailAddress).get();
        UserDto returnValue = new UserDto();
        returnValue.setHttpStatus(HttpStatus.ACCEPTED);
        BeanUtils.copyProperties(selectedUser, returnValue);
        return returnValue;
    }

    @Override
    public UserDto updateUser(UUID publicID, UserRequest userRequest) {
        User selectedUser = userRepository.findByPublicID(publicID).get();
        BeanUtils.copyProperties(userRequest, selectedUser);
        User storedUserDetails = userRepository.save(selectedUser);
        UserDto returnValue = new UserDto();
        returnValue.setHttpStatus(HttpStatus.ACCEPTED);
        BeanUtils.copyProperties(storedUserDetails, returnValue);
        return returnValue;
    }

    @Override
    public UserDto deleteUser(UUID publicID) {
        User selectedUser = userRepository.findByPublicID(publicID).get();
        Long userID = selectedUser.getId();
        userRepository.deleteById(userID);
        UserDto returnValue = new UserDto();
        returnValue.setHttpStatus(HttpStatus.ACCEPTED);
        returnValue.setLastName("Deleted");
        returnValue.setFirstName("Deleted");
        returnValue.setEmailAddress("Deleted");
        return returnValue;
    }

    @Override
    public UserDto createUser(UserDto userDto) {

        User newUser = new User();
        BeanUtils.copyProperties(userDto, newUser);

        newUser.setEncryptedPassword("encrypted" + userDto.getPassword());
        newUser.setPublicID();
        User storedUserDetails = userRepository.save(newUser);

        UserDto returnValue = new UserDto();
        returnValue.setHttpStatus(HttpStatus.ACCEPTED);
        BeanUtils.copyProperties(storedUserDetails, returnValue);
        return returnValue;
    }
}
