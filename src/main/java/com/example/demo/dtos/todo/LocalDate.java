package com.example.demo.dtos.todo;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocalDate {
    public int day;
    public int month;
    public int year;
}
