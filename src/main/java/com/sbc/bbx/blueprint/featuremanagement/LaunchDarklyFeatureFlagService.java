package com.sbc.bbx.blueprint.featuremanagement;

import com.launchdarkly.sdk.LDContext;
import com.launchdarkly.sdk.server.LDClient;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LaunchDarklyFeatureFlagService implements FeatureFlagService {

  @Autowired
  private LDClient client;

  @Override
  public boolean isFeatureFlagSet(String flag) {
    return isFeatureFlagSet(flag, null);
  }

  @Override
  public boolean isFeatureFlagSet(String flag, String context) {
    String contextKey = Optional.ofNullable(context).orElse("sample");
    LDContext ldContext = LDContext
      .builder(contextKey)
      .kind("microservices")
      .build();

    return client.boolVariation(flag, ldContext, false);
  }
}
