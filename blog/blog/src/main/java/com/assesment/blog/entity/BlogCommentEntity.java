package com.assesment.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "blog_comment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BlogCommentEntity extends BaseEntity {

    private String email;

    private String comment;

    @ManyToOne
    private BlogEntity blogEntity;
}
