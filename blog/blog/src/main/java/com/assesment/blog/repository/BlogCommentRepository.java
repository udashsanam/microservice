package com.assesment.blog.repository;


import com.assesment.blog.entity.BlogCommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogCommentRepository extends JpaRepository<BlogCommentEntity, Long> {

    List<BlogCommentEntity> findAllByBlogEntityId(Long blogId);
}
