package com.example.socialNetwork.service.impl;


import com.example.socialNetwork.dto.UserDto;
import com.example.socialNetwork.model.Useer;
import com.example.socialNetwork.repository.UserRepository;
import com.example.socialNetwork.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    public UserDto saveUser(UserDto usuarioDto) {

        if (!usuarioDto.getName().equals("")) {
            Useer user = new Useer(
                    usuarioDto.getId(),
                    usuarioDto.getName(),
                    usuarioDto.getLastName(),
                    usuarioDto.getDateBirth(),
                    usuarioDto.geteMail(),
                    usuarioDto.getUsername(),
                    usuarioDto.getPassword()
            );
            return domainToDto(userRepository.save(user));
        } else {
            return new UserDto();
        }
    }

    public UserDto getUserById(long id) {
        Optional<Useer> userOpt = userRepository.findById(id);
        return userOpt.map(this::domainToDto).orElse(null);
    }

    public UserDto getUserByUsername(String username) {
        Optional<Useer> userOpt = userRepository.getUserByUsername(username);
        return userOpt.map(this::domainToDto).orElse(null);
    }

    public List<UserDto> getListUser(){
        return userRepository.findAll()
                .stream()
                .map(this::domainToDto)
                .collect(Collectors.toList());
    }

    public boolean deleteUserById(long id) {
        try {
            userRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }


    public UserDto domainToDto(Useer user) {
        return new UserDto(
                user.id,
                user.name,
                user.lastName,
                user.dateBirth,
                user.eMail,
                user.username,
                user.password);
    }
}
