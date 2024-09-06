package com.example.user_service.service;

import com.example.user_service.configuration.customExceptions.InvalidCredentialsException;
import com.example.user_service.configuration.customExceptions.UserAlreadyExistsException;
import com.example.user_service.configuration.customExceptions.UserNotFoundWIthIdException;
import com.example.user_service.data.User;
import com.example.user_service.data.userDTO.*;
import com.example.user_service.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordService passwordService;

    @Autowired
    private UserMapper userMapper;

    public UserCommonResponseDTO createUser(UserCreateDTO userCreateDTO) throws UserAlreadyExistsException {

        User user = userMapper.toEntity(userCreateDTO);
        try {
            String hashedPassword = passwordService.hashPassword(user.getPassword());
            user.setPassword(hashedPassword);
            user.setJoinedDate(LocalDateTime.now());

            User savedUser = userRepository.save(user);

            return new UserCommonResponseDTO(userMapper.toDTO(savedUser));
        } catch (DataIntegrityViolationException e) {
            throw new UserAlreadyExistsException("A user with the email " + user.getEmail() + " already exists.");
        }

    }

    public UserCommonResponseDTO updateUser(UserUpdateDTO userUpdateDTO) throws UserNotFoundWIthIdException {

        Optional<User> existingUser = userRepository.findById(userUpdateDTO.getId());

        if (existingUser.isPresent()) {
            User dbUser = existingUser.get();

            String hashedPassword = passwordService.hashPassword(userUpdateDTO.getPassword());
            userUpdateDTO.setPassword(hashedPassword);

            userMapper.updateUserFromDTO(userUpdateDTO, dbUser);

            User updatedUser = userRepository.save(dbUser);

            return new UserCommonResponseDTO(userMapper.toDTO(updatedUser));
        } else {
            throw new UserNotFoundWIthIdException("User not found with id: " + userUpdateDTO.getId());
        }
    }

    public UserPaginatedDTO<UserResponseDTO> getUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page > 0 ? page - 1 : 0, size);
        Page<User> userPage = userRepository.findAll(pageable);

        List<UserResponseDTO> userResponses = userPage.stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());

        return new UserPaginatedDTO<>(userResponses,
                userPage.getNumber() + 1,
                userPage.getSize(),
                userPage.getTotalElements(),
                userPage.getTotalPages());
    }

    public  UserPaginatedDTO<UserResponseDTO> getAllUsers() {
        List<User> allUsers = userRepository.findAll();

        List<UserResponseDTO> userResponses = allUsers.stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());


        return new UserPaginatedDTO<>(
                userResponses,
                1,
                userResponses.size(),
                userResponses.size(),
                1);
    }

    public UserCommonResponseDTO deleteUser(int userId) throws UserNotFoundWIthIdException {
        Optional<User> existingUser = userRepository.findById(userId);

        if (existingUser.isPresent()) {
            userRepository.deleteById(userId);
            return new UserCommonResponseDTO(userMapper.toDTO(existingUser.get()));
        }
        else{
            throw new UserNotFoundWIthIdException("User not found with given id");
        }



    }

    public UserLoginResponseDTO login (String email, String password) throws InvalidCredentialsException{

        Optional<User> userOptional = userRepository.findByEmail(email);

        if(userOptional.isPresent()){
            User user = userOptional.get();

            if(passwordService.validatePassword(password, user.getPassword())){
                return new UserLoginResponseDTO(userMapper.toDTO(user), "");
            } else{
                throw new InvalidCredentialsException("Invalid password");
            }
        }else{
            throw new InvalidCredentialsException("User not found with given email");
        }
    }

    public UserPaginatedDTO<UserResponseDTO> searchUsers(String keyword, int page, int size){
        Pageable pageable = PageRequest.of(page > 0 ? page - 1 : 0, size);
        Page<User> userPage = userRepository.findByKeyword(keyword, pageable);

        List<UserResponseDTO> userResponses = userPage.stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());

        return new UserPaginatedDTO<>(userResponses,
                userPage.getNumber() + 1,
                userPage.getSize(),
                userPage.getTotalElements(),
                userPage.getTotalPages());
    }

}
