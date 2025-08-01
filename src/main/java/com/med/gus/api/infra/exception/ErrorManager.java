package com.med.gus.api.infra.exception;

import com.med.gus.api.domain.ValidException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorManager {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity manageError404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity manageError400(MethodArgumentNotValidException ex) {
        var errors = ex.getFieldErrors();

        return ResponseEntity.badRequest().body(errors.stream()
                .map(DataErrorValidation::new)
                .toList());
    }

    @ExceptionHandler(ValidException.class)
    public ResponseEntity manageError400BussinesRule(ValidException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    private record DataErrorValidation(String field, String messenger){
        public DataErrorValidation(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }

}
