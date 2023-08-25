package com.empresa.transportemayor.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ExceptionApp extends RuntimeException {

  private String message;
  private String status;
}
