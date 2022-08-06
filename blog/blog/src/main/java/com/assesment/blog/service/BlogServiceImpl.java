package com.assesment.blog.service;

import com.assesment.blog.entity.BlogEntity;
import com.assesment.blog.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class BlogServiceImpl extends BaseServiceImpl<BlogEntity, Long> implements IBlogService{

    private BlogRepository blogRepository;

    @Autowired
    public BlogServiceImpl(BlogRepository  blogRepository) {
        super(blogRepository);
        this.blogRepository = blogRepository;
    }
}
