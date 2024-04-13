package com.xbrain.vendasapi.controllers.handler;

import com.xbrain.vendasapi.exceptions.VendedorNaoEncontradoException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ControllerAdvice extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(erro -> errors.put(erro.getField(), erro.getDefaultMessage()));
        ErrorResponse errorResponse = new ErrorResponse("Erro ao validar os campos de entrada", null, errors);
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(VendedorNaoEncontradoException.class)
    public ResponseEntity<Object> handleVendedorNaoEncontradoExcepion(VendedorNaoEncontradoException vendedorNaoEncontradoException) {
        return ResponseEntity.unprocessableEntity().body(
                new ErrorResponse("Não é possível encontrar o vendedor.",
                        vendedorNaoEncontradoException.getMessage(), null));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception exception) {
        return ResponseEntity.internalServerError().body(new ErrorResponse("Ocorreu um erro inesperado.",
                        exception.getMessage(), null));
    }
}
