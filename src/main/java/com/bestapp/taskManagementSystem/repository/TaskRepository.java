package com.bestapp.taskManagementSystem.repository;

import com.bestapp.taskManagementSystem.model.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<TaskEntity, Integer> {
//    List<TaskEntity> findAllByUserEntityEmail(String email); // not working, maybe something wrong
}
