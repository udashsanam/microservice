package com.assesment.blog.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostCommentDto {

    private String name;

    private String email;

    private String comment;

    private Long blogId;
}
