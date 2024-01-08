package com.example.generic.crud.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(oneOf = {FactoryDto.class})
public abstract class BaseDto {
}
