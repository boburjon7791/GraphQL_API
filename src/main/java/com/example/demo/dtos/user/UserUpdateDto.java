package com.example.demo.dtos.user;

import com.example.demo.dtos.todo.TodoGetDto;
import com.example.demo.entities.Todo;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class UserUpdateDto {
    private Integer id;
    private String fullName;
    private String email;
    private String password;
    private List<TodoGetDto> todos;
}
