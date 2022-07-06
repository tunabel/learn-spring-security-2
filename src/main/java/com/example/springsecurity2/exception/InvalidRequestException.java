package com.example.springsecurity2.exception;

public class InvalidRequestException extends RuntimeException {

  public InvalidRequestException() {
    super();
  }

  public InvalidRequestException(String message, Throwable cause) {
    super(message, cause);
  }

  public InvalidRequestException(Throwable cause) {
    super(cause);
  }

  public InvalidRequestException(String message) {
    super(message);
  }
}
