package com.assessment.user.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "permission")
public class PermissionEntity extends BaseEntity{
}
