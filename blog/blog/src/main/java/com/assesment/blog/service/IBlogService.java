package com.assesment.blog.service;

import com.assesment.blog.dto.BlogPostDto;
import com.assesment.blog.entity.BlogEntity;
import com.assesment.blog.exception.NotFoundException;

public interface IBlogService extends IBaseService<BlogEntity, Long> {

    BlogEntity saveBlogPost(BlogPostDto blogPostDto) throws NotFoundException;

    boolean deleteBlogById(Long blogId);

}
