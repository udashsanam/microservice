package com.assesment.blog.service;

import com.assesment.blog.entity.BlogCommentEntity;
import com.assesment.blog.repository.BlogCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class BlogCommentServiceImpl extends BaseServiceImpl<BlogCommentEntity, Long> implements IBlogCommentService {

    private BlogCommentRepository blogCommentRepository;

    @Autowired
    public BlogCommentServiceImpl(BlogCommentRepository blogCommentRepository) {
        super(blogCommentRepository);
        this.blogCommentRepository = blogCommentRepository;
    }
}
