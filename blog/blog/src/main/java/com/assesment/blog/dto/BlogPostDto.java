package com.assesment.blog.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlogPostDto {

    private Long postedBy;

    private String body;

    private String title;

    private String author;
}
