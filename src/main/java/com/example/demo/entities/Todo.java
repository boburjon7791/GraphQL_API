package com.example.demo.entities;

import com.example.demo.enums.Category;
import com.example.demo.enums.Level;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Entity(name = "todos")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Column(nullable = false)
    private String title;

    private String description;

    @NotNull
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Category category;

    @NotNull
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Level level;

    @NotNull
    @Column(nullable = false)
    private LocalDate deadLine;
    private boolean completed;

    @NotNull
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL,
    optional = false)
    @JoinColumn(name = "user_id")
    private User user;
}
