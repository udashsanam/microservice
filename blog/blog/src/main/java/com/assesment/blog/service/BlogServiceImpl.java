package com.assesment.blog.service;

import com.assesment.blog.dto.BlogPostDto;
import com.assesment.blog.entity.BlogCommentEntity;
import com.assesment.blog.entity.BlogEntity;
import com.assesment.blog.exception.CannotDeleteException;
import com.assesment.blog.exception.NotFoundException;
import com.assesment.blog.exception.NotSavedException;
import com.assesment.blog.repository.BlogCommentRepository;
import com.assesment.blog.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogServiceImpl extends BaseServiceImpl<BlogEntity, Long> implements IBlogService{

    private BlogRepository blogRepository;

    private BlogCommentRepository blogCommentRepository;

    @Autowired
    public BlogServiceImpl(BlogRepository  blogRepository,
                           BlogCommentRepository blogCommentRepository) {
        super(blogRepository);
        this.blogRepository = blogRepository;
        this.blogRepository = blogRepository;
    }

    @Override
    public BlogEntity saveBlogPost(BlogPostDto blogPostDto) {

        if(blogPostDto.getPostedBy() == null) throw new NotFoundException(" Posted by cannot be null");

        BlogEntity blogEntity = new BlogEntity();
        blogEntity.setTitle(blogPostDto.getTitle());
        blogEntity.setCreatedBy(blogPostDto.getPostedBy());
        blogEntity.setAuthor(blogPostDto.getAuthor());
        blogEntity.setBody(blogPostDto.getBody());

        try {
            blogEntity = blogRepository.save(blogEntity);
            } catch (Exception ex){
            throw new NotSavedException("Blog post could not saved");
        }

        return blogEntity;
    }

    @Override
    public boolean deleteBlogById(Long blogId) {

        try {
            List<BlogCommentEntity>  blogCommentEntities = blogCommentRepository.findAllByBlogEntityId(blogId);
            blogCommentEntities.forEach(blogCommentEntity -> blogCommentRepository.deleteById(blogCommentEntity.getId()));
            blogRepository.deleteById(blogId);
        } catch (Exception ex){
            throw new CannotDeleteException("Error While deleting the blog");
        }

        return true;

    }
}
