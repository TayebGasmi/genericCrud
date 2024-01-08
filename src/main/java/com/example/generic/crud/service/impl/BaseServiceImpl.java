package com.example.generic.crud.service.impl;


import com.example.generic.crud.exception.EntityNotFoundException;
import com.example.generic.crud.mapper.BaseMapper;
import com.example.generic.crud.model.entity.BaseEntity;
import com.example.generic.crud.repository.BaseRepository;
import com.example.generic.crud.service.BaseService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;


@Slf4j

public abstract class BaseServiceImpl<E extends BaseEntity<I>, I extends Serializable, D> implements BaseService<E, I, D> {

    private final String entityClassName = ((ParameterizedType) getClass()
            .getGenericSuperclass()).getActualTypeArguments()[0].getTypeName();
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private BaseRepository<E, I> repository;
    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private BaseMapper<E, D> mapper;

    @Override
    @Transactional
    public E add(D dto) {
        return repository.save(mapper.toEntity(dto));
    }

    @Override
    @Transactional
    public E updateById(I id, D dto) {
        E entity = findById(id);
        mapper.partialUpdate(entity, dto);
        return repository.save(entity);
    }


    @Override
    @Transactional
    public void deleteById(I id) {
        findById(id);
        repository.deleteById(id);

    }

    @Override
    public E findById(I id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(entityClassName));
    }

    @Override
    public Page<E> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
