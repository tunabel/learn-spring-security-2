package com.example.springsecurity2.exception;

public class TokenRefreshException extends RuntimeException {

  public TokenRefreshException() {
    super();
  }

  public TokenRefreshException(String message) {
    super(message);
  }

  public TokenRefreshException(String message, Throwable cause) {
    super(message, cause);
  }

  public TokenRefreshException(Throwable cause) {
    super(cause);
  }

  protected TokenRefreshException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  public TokenRefreshException(String token, String message) {

  }

  private String token;
}
