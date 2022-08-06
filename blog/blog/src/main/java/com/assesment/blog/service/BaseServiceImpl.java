package com.assesment.blog.service;

import com.assesment.blog.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public class BaseServiceImpl<T extends BaseEntity, ID> implements IBaseService<T, ID> {

    private JpaRepository<T, ID> repository;
    public BaseServiceImpl(JpaRepository<T, ID> repository){
        this.repository = repository;
    }

    @Override
    public T save(T entity) {
        return repository.save(entity);
    }

    @Override
    public T update(T entity) {
        return repository.save(entity);
    }

    @Override
    public void deleteById(ID id) {
        repository.deleteById(id);
    }

    @Override
    public T findById(ID id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<T> findALL() {
        return repository.findAll();
    }
}
