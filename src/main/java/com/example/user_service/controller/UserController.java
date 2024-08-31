package com.example.user_service.controller;

import com.example.user_service.data.userDTO.*;
import com.example.user_service.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "")
    public UserCommonResponseDTO createUser(@Valid @RequestBody UserCreateDTO userCreateDTO) {
        System.out.println("---------------------------- here ------------------------");
        return userService.createUser(userCreateDTO);
    }

    @PutMapping(path = "")
    public UserCommonResponseDTO updateUser(@Valid @RequestBody UserUpdateDTO userUpdateDTO) {
        return userService.updateUser(userUpdateDTO);
    }

    @GetMapping(path = "")
    public UserPaginatedDTO<UserResponseDTO> getUsers(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size) {
        return userService.getUsers(page, size);
    }

    @GetMapping(path = "/all")
    public  UserPaginatedDTO<UserResponseDTO> getAllUsers(){
        return userService.getAllUsers();
    }

    @DeleteMapping(path = "/{id}")
    public UserCommonResponseDTO deleteUser(@PathVariable int id) {
        return userService.deleteUser(id);
    }

    @PostMapping(path = "/login")
    public UserLoginResponseDTO login(@Valid @RequestBody UserLoginDTO userLoginDTO){
        return userService.login(userLoginDTO.getEmail(), userLoginDTO.getPassword());
    }

    @GetMapping(path = "/search")
    public UserPaginatedDTO<UserResponseDTO> searchUsers(@RequestParam(defaultValue = "") String keyword,@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size) {
        return userService.searchUsers(keyword, page, size);
    }
}
