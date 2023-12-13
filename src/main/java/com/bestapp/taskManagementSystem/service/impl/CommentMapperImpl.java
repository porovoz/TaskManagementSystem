package com.bestapp.taskManagementSystem.service.impl;

import com.bestapp.taskManagementSystem.dto.comment.CommentDTO;
import com.bestapp.taskManagementSystem.model.CommentEntity;
import com.bestapp.taskManagementSystem.service.CommentMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommentMapperImpl implements CommentMapper {

    @Override
    public CommentEntity toCommentEntity(CommentDTO commentDTO) {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setId(commentDTO.getCommentId());
        commentEntity.setText(commentDTO.getText());
        return commentEntity;
    }

    @Override
    public CommentDTO toCommentDTO(CommentEntity commentEntity) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setTaskAuthorId(commentEntity.getUserEntity().getId());
        commentDTO.setAuthorFirstName(commentEntity.getUserEntity().getFirstName());
        commentDTO.setCommentId(commentEntity.getId());
        commentDTO.setText(commentEntity.getText());
        return commentDTO;
    }

    @Override
    public List<CommentDTO> toCommentDTOList(List<CommentEntity> commentEntityList) {
        List<CommentDTO> commentDTOList = new ArrayList<>();
        for (CommentEntity commentEntity : commentEntityList) {
            CommentDTO commentDTO = toCommentDTO(commentEntity);
            commentDTOList.add(commentDTO);
        }
        return commentDTOList;
    }
}
