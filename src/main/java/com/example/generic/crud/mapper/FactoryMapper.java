package com.example.generic.crud.mapper;

import com.example.generic.crud.dto.FactoryDto;
import com.example.generic.crud.model.entity.Factory;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface FactoryMapper extends BaseMapper<Factory, FactoryDto> {
}
