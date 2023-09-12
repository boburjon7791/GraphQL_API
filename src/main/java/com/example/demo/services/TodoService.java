package com.example.demo.services;

import com.example.demo.dtos.todo.LocalDate;
import com.example.demo.dtos.todo.TodoCreateDto;
import com.example.demo.dtos.todo.TodoGetDto;
import com.example.demo.dtos.todo.TodoUpdateDto;
import com.example.demo.dtos.user.UserGetDto;
import com.example.demo.enums.Category;
import com.example.demo.enums.Level;
import lombok.NonNull;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface TodoService {
    TodoGetDto create(@NonNull TodoCreateDto dto);
    TodoGetDto update(@NonNull TodoUpdateDto dto);
    TodoGetDto get(@NonNull Integer id);
    void delete(@NonNull Integer id);
    List<TodoGetDto> get();
    List<TodoGetDto> get(Category category);
    List<TodoGetDto> get(Level level);
    List<TodoGetDto> get(LocalDate localDate1,LocalDate localDate2);
}
