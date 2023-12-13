package com.bestapp.taskManagementSystem.dto.comment;

import lombok.Data;

@Data
public class CommentDTO {
    private Integer taskAuthorId;
    private String authorFirstName;
    private Integer commentId;
    private String text;
}
