package com.assesment.blog.repository;


import com.assesment.blog.entity.BlogCommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogCommentRepository extends JpaRepository<BlogCommentEntity, Long> {
}
