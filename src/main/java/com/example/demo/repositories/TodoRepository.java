package com.example.demo.repositories;

import com.example.demo.dtos.todo.TodoGetDto;
import com.example.demo.entities.Todo;
import com.example.demo.enums.Category;
import com.example.demo.enums.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer>, JpaSpecificationExecutor<Todo> {
    @Modifying
    @Transactional
    @Query(nativeQuery = true,value = "delete from todos where id=?1")
    void deleteWithId(Integer id);

    List<Todo> findAllByCategory(Category category);


    List<Todo> findAllByLevel(Level level);

    @Query(value = "from todos t where t.deadLine between ?1 and ?2")
    List<Todo> findAllBetweenDeadLine(LocalDate date1, LocalDate date2);
}