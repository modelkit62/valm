package com.example.cullera.controller;


import com.example.cullera.model.RequestDataAttribute;
import com.example.cullera.model.ResponseDataAttributes;
import com.example.cullera.service.CulleraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CulleraController {

    @Autowired
    private CulleraService culleraService;

    @PostMapping("/cullera/{id}")
    public ResponseDataAttributes getAttributes(@Valid @RequestBody RequestDataAttribute attribute, @PathVariable ("id") String id) throws Exception {
        if(attribute.getValor().equals("2")) {
            return culleraService.getAttributest(id);
        }
            throw new Exception("Joder");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    Collection<?> exceptionHandler(MethodArgumentNotValidException e){
        Collection<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        Collection<FieldError> errors = fieldErrors.stream().map(fieldError -> new FieldError(fieldError.getObjectName(), fieldError.getField(), fieldError.getDefaultMessage())).collect(Collectors.toList());
        return errors;
    }

}
