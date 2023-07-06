package com.sbc.bbx.blueprint.featuremanagement;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class FeatureFlagAspect {

  private FeatureFlagService featureFlagService;

  public FeatureFlagAspect(FeatureFlagService featureFlagService) {
    this.featureFlagService = featureFlagService;
  }

  @Before(
    "execution (* com.sbc.bbx.sample..*(..)) && @annotation(checkFeatureFlag)"
  )
  public void checkFeatureFlag(
    JoinPoint joinPoint,
    CheckFeatureFlag checkFeatureFlag
  ) {
    String flag = checkFeatureFlag.flag();
    if (!featureFlagService.isFeatureFlagSet(flag, null)) {
      throw new FeatureNotEnabledException();
    }
  }
}
