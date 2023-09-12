package com.example.demo.mappers;

import com.example.demo.dtos.todo.TodoGetDto;
import com.example.demo.dtos.user.UserCreateDto;
import com.example.demo.dtos.user.UserGetDto;
import com.example.demo.dtos.user.UserUpdateDto;
import com.example.demo.entities.Todo;
import com.example.demo.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper USER_MAPPER = Mappers.getMapper(UserMapper.class);
    @Mapping(target = "id",ignore = true)
    @Mapping(target = "todos",ignore = true)
    User toEntity(UserCreateDto dto);
    default List<UserGetDto> toDto(List<User> users){
        if (users==null || users.isEmpty()) {
            return new ArrayList<>();
        }
        return users.stream()
                .map(this::toDto)
                .toList();
    }
    default User toEntity(UserUpdateDto dto){
        List<TodoGetDto> todos = dto.getTodos();
        List<Todo> todoList = TodoMapper.TODO_MAPPER.toEntity(todos);
        return User.builder()
                .id(dto.getId())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .todos(todoList)
                .fullName(dto.getFullName()).build();
    }
    default UserGetDto toDto(User user){
        List<Todo> todos = user.getTodos();
        List<TodoGetDto> dtoList = TodoMapper.TODO_MAPPER.toDto(todos);
        return UserGetDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .todos(dtoList)
                .fullName(user.getFullName())
                .password(user.getPassword())
                .build();
    }
}
