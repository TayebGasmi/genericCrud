package com.example.generic.crud.controller;


import com.example.generic.crud.dto.BaseDto;
import com.example.generic.crud.model.entity.BaseEntity;
import com.example.generic.crud.service.impl.BaseServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;


public abstract class BaseController<E extends BaseEntity<I>, I extends Serializable, D> {


    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private BaseServiceImpl<E, I, D> baseService;

    @Operation(summary = "Save new entity")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Entity created"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid input")})

    @PostMapping
    public ResponseEntity<E> save(@RequestBody @NotNull @Valid @Schema(description = "DTO for saving", implementation = BaseDto.class) D dto) {

        return new ResponseEntity<>(baseService.add(dto), HttpStatus.CREATED);
    }

    @Operation(summary = "Get entity by id")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Found the entity"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid id supplied"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Entity not found")})
    @Parameters(value = {
            @Parameter(name = "id", description = "Id of entity to be searched", required = true, example = "1", schema = @Schema(implementation = Long.class))})
    @GetMapping("/{id}")
    public ResponseEntity<E> getById(@PathVariable I id) {
        return new ResponseEntity<>(baseService.findById(id), HttpStatus.OK);
    }

    @Operation(summary = "Update entity by id")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Found the entity"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid id supplied"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Entity not found")})
    @Parameters(value = {@Parameter(name = "id", description = "Id of entity to be searched", required = true, example = "1", schema = @Schema(implementation = Long.class))})
    @PatchMapping("/{id}")
    public ResponseEntity<E> update(@PathVariable I id, @Valid @NotNull @RequestBody D dto) {


        return new ResponseEntity<>(baseService.updateById(id, dto), HttpStatus.OK);
    }

    @Operation(summary = "Delete entity by id")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "204", description = "Entity deleted"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Entity not found")})
    @Parameters(value = {@Parameter(name = "id", description = "Id of entity to be searched", required = true, example = "1", schema = @Schema(implementation = Long.class))})

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable I id) {
        baseService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Get all entities")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Found the entity"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Entity not found")})
    @GetMapping
    public ResponseEntity<Page<E>> getAllWithPagination(Pageable pageable) {
        return new ResponseEntity<>(baseService.findAll(pageable), HttpStatus.OK);
    }


}
