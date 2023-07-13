package com.hang.backend.repository;

import com.hang.backend.model.ToDoList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListRepository extends JpaRepository<ToDoList, Long> {
    List<ToDoList> findByListNameLike(String listName);
}
