package com.sbc.bbx.blueprint.featuremanagement;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(
  value = HttpStatus.FORBIDDEN,
  reason = "Feature is not enabled."
)
public class FeatureNotEnabledException extends RuntimeException {

  private static final String MESSAGE = "Feature is not enabled.";

  public FeatureNotEnabledException() {
    super(MESSAGE);
  }
}
