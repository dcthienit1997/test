package com.sbc.bbx.blueprint.featuremanagement;

import jakarta.validation.constraints.NotEmpty;
import java.time.Duration;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@ConfigurationProperties("launchdarkly")
public class LaunchDarklyProperties {

  @NotEmpty
  private String sdkKey;

  private Duration connectTimeout = Duration.ofSeconds(10);

  private Duration socketTimeout = Duration.ofSeconds(30);

  private Duration eventFlushInterval = Duration.ofSeconds(60);

  private Duration startWait = Duration.ofSeconds(60);

  private String streamingEndpoint = "https://stream.launchdarkly.com";

  private String pollingEndpoint = "https://sdk.launchdarkly.com";

  private String eventsEndpoint = "https://events.launchdarkly.com";
}
