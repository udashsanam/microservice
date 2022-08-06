package com.assesment.blog.controller;

import com.assesment.blog.dto.PostCommentDto;
import com.assesment.blog.dto.ResponseDto;
import com.assesment.blog.entity.BlogCommentEntity;
import com.assesment.blog.exception.NotSavedException;
import com.assesment.blog.service.IBlogCommentService;
import com.assesment.blog.service.IBlogService;
import com.assesment.blog.util.ApiConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping(ApiConstant.COMMENT)
public class PostCommentController {

    private IBlogCommentService blogCommentService;

    private IBlogService blogService;

    @Autowired
    public PostCommentController(IBlogCommentService blogCommentService,
                                 IBlogService blogService) {
        this.blogCommentService = blogCommentService;
        this.blogService = blogService;
    }

    @PostMapping
    public ResponseEntity<ResponseDto> postComment(@RequestBody PostCommentDto postCommentDto) {
        BlogCommentEntity blogCommentEntity = new BlogCommentEntity();
        try {
            blogCommentEntity.setComment(postCommentDto.getComment());
            blogCommentEntity.setEmail(postCommentDto.getEmail());
            blogCommentEntity.setBlogEntity(blogService.findById(postCommentDto.getBlogId()));
            blogCommentEntity.setCreateDate(new Date());
            blogCommentEntity = blogCommentService.save(blogCommentEntity);
        } catch (Exception exception){
            throw new NotSavedException(" Error while saving blog comment ");
        }
        return new ResponseEntity<>(new ResponseDto("Successfully saved ", blogCommentEntity), HttpStatus.OK);
    }

}
