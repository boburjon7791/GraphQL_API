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
public class TodoGetDto {
    private Integer id;
    private String title;
    private String description;
    private Category category;
    private Level level;
    private LocalDate deadLine;
    private boolean completed;
    private Integer userId;
}
