package com.example.user_service.data.userDTO;

import com.example.user_service.data.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toEntity(UserCreateDTO userCreateDTO);

    UserResponseDTO toDTO(User user);

    void updateUserFromDTO(UserUpdateDTO userUpdateDTO, @MappingTarget User user);
}
