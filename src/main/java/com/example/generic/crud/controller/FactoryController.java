package com.example.generic.crud.controller;

import com.example.generic.crud.dto.FactoryDto;
import com.example.generic.crud.model.entity.Factory;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FactoryController extends BaseController<Factory, java.lang.Long, FactoryDto> {
}
