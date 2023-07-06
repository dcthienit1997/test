package com.sbc.bbx.blueprint.enums;

import lombok.Getter;

@Getter
public enum ErrorAttributesKey {
  CODE("code"),
  ERROR_CODE("errorCode"),
  MESSAGE("message"),
  TIME("timestamp");

  private final String key;

  ErrorAttributesKey(String key) {
    this.key = key;
  }
}
