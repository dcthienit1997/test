package com.sbc.bbx.blueprint.exception;

public class UnAuthorizedException extends RuntimeException {

  public UnAuthorizedException(String message) {
    super(message);
  }

  public UnAuthorizedException() {}
}
