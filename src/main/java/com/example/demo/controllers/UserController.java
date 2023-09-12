package com.example.demo.controllers;

import com.example.demo.dtos.user.UserCreateDto;
import com.example.demo.dtos.user.UserGetDto;
import com.example.demo.dtos.user.UserUpdateDto;
import com.example.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @QueryMapping
    public UserGetDto getUser(@Argument Integer id){
        return userService.get(id);
    }
    @MutationMapping
    public UserGetDto createUser(@Argument UserCreateDto dto){
        return userService.create(dto);
    }
    @MutationMapping
    public UserGetDto updateUser(@Argument UserUpdateDto dto){
        return userService.update(dto);
    }
    @MutationMapping
    public boolean deleteUser(@Argument Integer id){
        try {
            userService.delete(id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    @QueryMapping
    public List<UserGetDto> getUsers(){
        return userService.get();
    }
}
