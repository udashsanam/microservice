package com.assessment.authentication.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity implements Serializable {


    private Long id;

    private String name;

    private Date createDate= new Date();

    private Long createdBy;

    private Long updateBy;

    private Date updatedDate;


}
