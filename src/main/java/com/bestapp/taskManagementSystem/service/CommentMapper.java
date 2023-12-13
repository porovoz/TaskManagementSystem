package com.bestapp.taskManagementSystem.service;

import com.bestapp.taskManagementSystem.dto.comment.CommentDTO;
import com.bestapp.taskManagementSystem.model.CommentEntity;

import java.util.List;

public interface CommentMapper {
    CommentEntity toCommentEntity(CommentDTO commentDTO);
    CommentDTO toCommentDTO(CommentEntity commentEntity);
    List<CommentDTO> toCommentDTOList(List<CommentEntity> commentEntityList);
}
