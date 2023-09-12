package com.example.demo.services;

import com.example.demo.dtos.todo.*;
import com.example.demo.entities.Todo;
import com.example.demo.entities.User;
import com.example.demo.enums.Category;
import com.example.demo.enums.Level;
import com.example.demo.repositories.TodoRepository;
import com.example.demo.repositories.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Objects;

import static com.example.demo.mappers.TodoMapper.TODO_MAPPER;

@RequiredArgsConstructor
@Service
public class TodoServiceImpl implements TodoService {
    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    @Override
    public TodoGetDto create(@NonNull TodoCreateDto dto) {
        try {
            Todo todo = TODO_MAPPER.toEntity(dto);
            LocalDateInput deadLine = dto.getDeadLine();
            LocalDate build = LocalDate.builder().year(deadLine.year).month(deadLine.month).day(deadLine.day).build();
            java.time.LocalDate localDate = java.time.LocalDate.of(build.year, build.month, build.day);
            User user = userRepository.findById(dto.getUserId()).orElseThrow();
            todo.setUser(user);
            todo.setDeadLine(localDate);
            TodoGetDto dto1 = TODO_MAPPER.toDto(todoRepository.save(todo));
            dto1.setDeadLine(build);
            dto1.setUserId(todo.getUser().getId());
            return dto1;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    @Transactional
    public TodoGetDto update(@NonNull TodoUpdateDto dto) {
        try {
            LocalDateInput deadLine = dto.getDeadLine();
            LocalDate build = LocalDate.builder().day(deadLine.day).month(deadLine.month).year(deadLine.year).build();
            java.time.LocalDate localDate = java.time.LocalDate.of(build.year, build.month, build.day);
            Todo todo = todoRepository.findById(dto.getId()).orElseThrow();
                todo.setCategory(Objects.requireNonNullElse(dto.getCategory(), todo.getCategory()));
                todo.setCompleted(dto.isCompleted());
                todo.setLevel(Objects.requireNonNullElse(dto.getLevel(), todo.getLevel()));
                todo.setTitle(Objects.requireNonNullElse(dto.getTitle(), todo.getTitle()));
                todo.setDescription(Objects.requireNonNullElse(dto.getDescription(), todo.getDescription()));
                todo.setDeadLine(localDate);
            TodoGetDto dto1 = TODO_MAPPER.toDto(todoRepository.save(todo));
            dto1.setDeadLine(build);
            dto1.setUserId(todo.getUser().getId());
            return dto1;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public TodoGetDto get(@NonNull Integer id) {
        try {
            Todo todo = todoRepository.findById(id).orElseThrow();
            java.time.LocalDate deadLine = todo.getDeadLine();
            LocalDate localDate = LocalDate.builder().year(deadLine.getYear())
                    .month(deadLine.getMonthValue()).day(deadLine.getDayOfMonth()).build();
            TodoGetDto dto = TODO_MAPPER.toDto(todo);
            dto.setDeadLine(localDate);
            dto.setUserId(todo.getUser().getId());
            return dto;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public void delete(@NonNull Integer id) {
        try {
            todoRepository.deleteWithId(id);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public List<TodoGetDto> get() {
        try {
            List<Todo> all = todoRepository.findAll();
            List<TodoGetDto> dto = TODO_MAPPER.toDto(all);
            method(all,dto);
            return dto;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public List<TodoGetDto> get(Category category) {
        try {
            List<Todo> allByCategory = todoRepository.findAllByCategory(category);
            List<TodoGetDto> dto = TODO_MAPPER.toDto(allByCategory);
            method(allByCategory,dto);
            return dto;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public List<TodoGetDto> get(Level level) {
        try {
            List<Todo> allByLevel = todoRepository.findAllByLevel(level);
            List<TodoGetDto> dto = TODO_MAPPER.toDto(allByLevel);
            method(allByLevel,dto);
            return dto;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
    private static void method(List<Todo> todos,List<TodoGetDto> getDtoList){
        for (int i = 0; i < todos.size(); i++) {
            java.time.LocalDate deadLine = todos.get(i).getDeadLine();
            LocalDate build = LocalDate.builder()
                    .month(deadLine.getMonthValue())
                    .year(deadLine.getYear())
                    .day(deadLine.getDayOfMonth()).build();
            getDtoList.get(i).setDeadLine(build);
            getDtoList.get(i).setUserId(todos.get(i).getUser().getId());
        }
    }

    @Override
    public List<TodoGetDto> get(LocalDate localDate1,LocalDate localDate2) {
        try {
            java.time.LocalDate date1 = java.time.LocalDate.of(localDate1.year, localDate1.month, localDate1.day);
            java.time.LocalDate date2 = java.time.LocalDate.of(localDate2.year, localDate2.month, localDate2.day);
            List<Todo> all = todoRepository.findAllBetweenDeadLine(date1,date2);
            List<TodoGetDto> dto = TODO_MAPPER.toDto(all);
            method(all,dto);
            return dto;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
