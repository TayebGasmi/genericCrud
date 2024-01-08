package com.example.generic.crud.service;


import com.example.generic.crud.model.entity.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;

public interface BaseService<E extends BaseEntity<I>, I extends Serializable, D> {
    E add(D dto);

    E updateById(I id, D dto);

    E findById(I id);

    void deleteById(I id);

    Page<E> findAll(Pageable pageable);


}
