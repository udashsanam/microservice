package com.assessment.user.service;

import java.util.List;

public interface IBaseService <T, ID>{

    T save(T entity);

    T update(T entity);

    void deleteById(ID id);

    T findById(ID id);

    List<T> findALL();
}
