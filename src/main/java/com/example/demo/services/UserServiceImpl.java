package com.example.demo.services;

import com.example.demo.dtos.todo.TodoGetDto;
import com.example.demo.dtos.user.UserCreateDto;
import com.example.demo.dtos.user.UserGetDto;
import com.example.demo.dtos.user.UserUpdateDto;
import com.example.demo.entities.Todo;
import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

import static com.example.demo.mappers.UserMapper.USER_MAPPER;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public UserGetDto create(@NonNull UserCreateDto dto) {
        try {
            User user = USER_MAPPER.toEntity(dto);
            User save = userRepository.save(user);
            return USER_MAPPER.toDto(save);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    @Transactional
    public UserGetDto update(@NonNull UserUpdateDto dto) {
        try {
            User entity = USER_MAPPER.toEntity(dto);
            User user = userRepository.findById(dto.getId()).orElseThrow();
            user.setTodos(Objects.requireNonNullElse(entity.getTodos(),user.getTodos()));
            user.setEmail(Objects.requireNonNullElse(entity.getEmail(),user.getEmail()));
            user.setFullName(Objects.requireNonNullElse(entity.getFullName(),user.getFullName()));
            user.setPassword(Objects.requireNonNullElse(entity.getPassword(),user.getPassword()));
            User save = userRepository.save(user);
            UserGetDto dto1 = USER_MAPPER.toDto(save);
            dto1.getTodos().forEach(getDto -> getDto.setUserId(dto.getId()));
            return dto1;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public UserGetDto get(@NonNull Integer id) {
        try {
            User user = userRepository.findById(id).orElseThrow();
            UserGetDto dto = USER_MAPPER.toDto(user);
            dto.getTodos().forEach(getDto -> getDto.setUserId(id));
            return dto;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public void delete(@NonNull Integer id) {
         try {
             userRepository.deleteWithId(id);
         }catch (Exception e){
             e.printStackTrace();
             throw new RuntimeException();
         }
    }

    @Override
    public List<UserGetDto> get() {
        try {
            List<User> all = userRepository.findAll();
            List<UserGetDto> dto = USER_MAPPER.toDto(all);
            Map<UserGetDto,User> map=new HashMap<>();
            for (int i = 0; i < all.size(); i++) {
                map.put(dto.get(i),all.get(i));
            }
            map.forEach((userGetDto, user) -> {
                List<Todo> todos = user.getTodos();
                List<TodoGetDto> getDtoTodos = userGetDto.getTodos();
                for (int i = 0; i < todos.size(); i++) {
                    LocalDate deadLine = todos.get(i).getDeadLine();
                    com.example.demo.dtos.todo.LocalDate localDate = com.example.demo.dtos.todo.LocalDate.builder()
                            .year(deadLine.getYear())
                            .month(deadLine.getMonthValue())
                            .day(deadLine.getDayOfMonth())
                            .build();
                    getDtoTodos.get(i).setDeadLine(localDate);
                    getDtoTodos.get(i).setUserId(todos.get(i).getUser().getId());
                }
            });
            return dto;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
