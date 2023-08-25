package com.empresa.transportemayor.config;

import com.empresa.transportemayor.exception.ExceptionApp;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BaseAdviceController {

  @ResponseStatus(code = HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public Map<String, String> handleValidateException(MethodArgumentNotValidException e) {
    Map<String, String> error = new HashMap<>();
    error.put(
        Objects.requireNonNull(e.getBindingResult().getFieldError()).getField(),
        e.getBindingResult().getFieldError().getDefaultMessage());
    return error;
  }

  @ResponseStatus(code = HttpStatus.CONFLICT)
  @ExceptionHandler(ExceptionApp.class)
  public Map<String, String> existDara(ExceptionApp e) {
    Map<String, String> error = new HashMap<>();
    error.put("status", e.getStatus());
    error.put("message", e.getMessage());
    return error;
  }
}
