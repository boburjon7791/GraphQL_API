package com.example.demo.mappers;

import com.example.demo.dtos.todo.LocalDateInput;
import com.example.demo.dtos.todo.TodoCreateDto;
import com.example.demo.dtos.todo.TodoGetDto;
import com.example.demo.entities.Todo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TodoMapper {
    TodoMapper TODO_MAPPER = Mappers.getMapper(TodoMapper.class);
    @Mapping(target = "userId",ignore = true)
    @Mapping(target = "deadLine",ignore = true)
    TodoGetDto toDto(Todo todo);

    @Mapping(target = "user",ignore = true)
    @Mapping(target = "deadLine",ignore = true)
    Todo toEntity(TodoGetDto dto);
    @Mapping(target = "id",ignore = true)
    @Mapping(target = "user",ignore = true)
    @Mapping(target = "completed",ignore = true)
    @Mapping(target = "deadLine",ignore = true)
    Todo toEntity(TodoCreateDto dto);
    default List<Todo> toEntity(List<TodoGetDto> dtoList){
        if (dtoList==null || dtoList.isEmpty()) {
            return new LinkedList<>();
        }
        return dtoList.stream()
                .map(this::toEntity)
                .toList();
    }

    default List<TodoGetDto> toDto(List<Todo> dtoList){
        if (dtoList==null || dtoList.isEmpty()) {
            return new LinkedList<>();
        }
        return dtoList.stream()
                .map(this::toDto)
                .toList();
    }
}
