package com.example.generic.crud.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ValidationError implements SubError {

    private String object;
    private String field;
    private Object rejectedValue;
    private String message;

    public Set<SubError> getSubErrors(MethodArgumentNotValidException ex) {
        Set<SubError> subErrors = new HashSet<>();
        ex.getBindingResult().getFieldErrors().forEach(fieldError ->
                subErrors.add(ValidationError.builder()
                        .object(fieldError.getObjectName())
                        .field(fieldError.getField())
                        .rejectedValue(fieldError.getRejectedValue())
                        .message(fieldError.getDefaultMessage())
                        .build())
        );
        return subErrors;
    }

}

