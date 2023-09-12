package com.example.demo.dtos.todo;

import com.example.demo.enums.Category;
import com.example.demo.enums.Level;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class TodoCreateDto {
    private String title;
    private String description;
    private Category category;
    private Level level;
    private LocalDateInput deadLine;
    private Integer userId;
}
