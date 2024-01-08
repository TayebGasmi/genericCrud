package com.example.generic.crud.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

public interface BaseMapper<E, D> {
    E toEntity(D dto);

    D toDto(E entity);

    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    void partialUpdate(@MappingTarget E entity, D dto);


}

