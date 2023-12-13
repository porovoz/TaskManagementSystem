package com.bestapp.taskManagementSystem.repository;

import com.bestapp.taskManagementSystem.model.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
    List<CommentEntity> findCommentEntitiesByTaskEntity_Id(Integer taskId);
    Optional<CommentEntity> findByIdAndTaskEntity_Id(Integer commentId, Integer taskId);
    void deleteAllByTaskEntity_Id(Integer taskId);
}
