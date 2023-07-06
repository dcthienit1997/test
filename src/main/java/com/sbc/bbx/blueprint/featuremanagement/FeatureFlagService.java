package com.sbc.bbx.blueprint.featuremanagement;

public interface FeatureFlagService {
  boolean isFeatureFlagSet(String flag);
  boolean isFeatureFlagSet(String flag, String context);
}
