package com.example.demo.controllers;

import com.example.demo.dtos.todo.LocalDate;
import com.example.demo.dtos.todo.TodoCreateDto;
import com.example.demo.dtos.todo.TodoGetDto;
import com.example.demo.dtos.todo.TodoUpdateDto;
import com.example.demo.enums.Category;
import com.example.demo.enums.Level;
import com.example.demo.services.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class TodoController {
    private final TodoService todoService;

    @QueryMapping
    public List<TodoGetDto> getTodos(){
        return todoService.get();
    }
    @QueryMapping
    public List<TodoGetDto> getTodosByLevel(@Argument Level level){
        return todoService.get(level);
    }
    @QueryMapping
    public List<TodoGetDto> getTodosByCategory(@Argument Category category){
        return todoService.get(category);
    }
    @QueryMapping
    public List<TodoGetDto> getTodosByDate(@Argument LocalDate date1,@Argument LocalDate date2){
        return todoService.get(date1,date2);
    }
    @MutationMapping
    public TodoGetDto createTodo(@Argument TodoCreateDto dto){
        return todoService.create(dto);
    }
    @MutationMapping
    public TodoGetDto updateTodo(@Argument TodoUpdateDto dto){
        return todoService.update(dto);
    }
    @MutationMapping
    public boolean deleteTodo(@Argument Integer id){
        try {
            todoService.delete(id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    @QueryMapping
    public TodoGetDto getTodo(@Argument Integer id){
        return todoService.get(id);
    }
}
