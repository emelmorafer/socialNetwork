package com.example.socialNetwork.service;

import com.example.socialNetwork.dto.UserDto;

import java.util.List;

public interface UserService {

    public UserDto saveUser(UserDto userDto);

    public UserDto getUserById(long id);

    public UserDto getUserByUsername(String username);

    public List<UserDto> getListUser();

    public boolean deleteUserById(long id);
}
