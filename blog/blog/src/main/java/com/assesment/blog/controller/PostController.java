package com.assesment.blog.controller;

import com.assesment.blog.dto.BlogPostDto;
import com.assesment.blog.dto.ResponseDto;
import com.assesment.blog.entity.BlogEntity;
import com.assesment.blog.exception.NotFoundException;
import com.assesment.blog.exception.NotSavedException;
import com.assesment.blog.service.IBlogService;
import com.assesment.blog.util.ApiConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiConstant.POST)
public class PostController {

    private IBlogService blogService;

    @Autowired
    public PostController(IBlogService blogService) {
        this.blogService = blogService;
    }

    @PostMapping()
    public ResponseEntity<ResponseDto>  blogPost(@RequestBody BlogPostDto blogPostDto){

        BlogEntity blogEntity = new BlogEntity();
        try {
             blogEntity = blogService.saveBlogPost(blogPostDto);
        } catch (Exception ex){
            throw new NotSavedException("Error while saving the blog post");
        }
        if(blogEntity.getId() ==null){
            return new ResponseEntity<>(new ResponseDto("Saved unsuccessful"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ResponseDto("Save successfully", blogEntity), HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<ResponseDto> editBlog(@RequestBody BlogEntity blogEntity){
        if(blogEntity.getId() == null) throw new NotFoundException("Blog id must not be null");

        try {
            blogEntity = blogService.update(blogEntity);
        } catch (Exception ex){
            throw new NotSavedException("Could not updated the blog post ");
        }
        return new ResponseEntity<>(new ResponseDto("successfully updated", blogEntity), HttpStatus.OK);

    }

    @GetMapping(ApiConstant.FIND_ALL)
    public ResponseEntity<ResponseDto> findAllBlog(){
        List<BlogEntity> blogEntities = blogService.findALL();
        if(blogEntities == null || blogEntities.size() == 0 ) {
            return new ResponseEntity<>(new ResponseDto("Empty list"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ResponseDto("Sucessfully fetched", blogEntities), HttpStatus.OK);
    }

    @GetMapping(ApiConstant.FIND_BY_ID)
    public ResponseEntity<ResponseDto> findById(@PathVariable("id") Long id){
        BlogEntity blogEntity = blogService.findById(id);
        if(blogEntity == null){
            return new ResponseEntity<>(new ResponseDto("Not found "), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new ResponseDto("Successfully fetched", blogEntity), HttpStatus.OK);
    }

    @DeleteMapping(ApiConstant.DELETE_BY_ID)
    public ResponseEntity<ResponseDto> deleteById(@PathVariable("id") Long id) {

        boolean isDeleted = blogService.deleteBlogById(id);
        if(isDeleted){
            return new ResponseEntity<>(new ResponseDto("Successfully deleted"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDto("Error while deleting the blog"), HttpStatus.NOT_ACCEPTABLE);
    }

}
