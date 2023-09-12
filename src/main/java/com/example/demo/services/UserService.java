package com.example.demo.services;

import com.example.demo.dtos.user.UserCreateDto;
import com.example.demo.dtos.user.UserGetDto;
import com.example.demo.dtos.user.UserUpdateDto;
import lombok.NonNull;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserGetDto create(@NonNull UserCreateDto dto);
    UserGetDto update(@NonNull UserUpdateDto dto);
    UserGetDto get(@NonNull Integer id);
    void delete(@NonNull Integer id);
    List<UserGetDto> get();
}
