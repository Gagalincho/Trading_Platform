package com.example.backend.mapper;

import com.example.backend.dto.UserDTO;
import com.example.backend.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {
    public static UserDTO toDTO(User user) {
        return new UserDTO(
            user.getUsername(),
            user.getName(),
            user.getEmail(),
            user.getCurrentBalance()
        );
    }

    public static List<UserDTO> toDTOList(List<User> users) {
        return users.stream().map(UserMapper::toDTO).collect(Collectors.toList());
    }
}
