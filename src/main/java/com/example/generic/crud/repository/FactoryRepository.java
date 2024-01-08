package com.example.generic.crud.repository;

import com.example.generic.crud.model.entity.Factory;
import org.springframework.stereotype.Repository;

@Repository
public interface FactoryRepository extends BaseRepository<Factory, Long> {
}
