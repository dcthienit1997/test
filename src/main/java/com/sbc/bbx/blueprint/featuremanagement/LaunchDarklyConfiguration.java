package com.sbc.bbx.blueprint.featuremanagement;

import com.launchdarkly.sdk.server.Components;
import com.launchdarkly.sdk.server.LDClient;
import com.launchdarkly.sdk.server.LDConfig;
import jakarta.annotation.PreDestroy;
import java.io.IOException;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(LaunchDarklyProperties.class)
public class LaunchDarklyConfiguration {

  private LDClient launchdarklyClient;

  @Bean
  public LDClient launchdarklyClient(LaunchDarklyProperties properties) {
    // For more configuration: https://javadoc.io/doc/com.launchdarkly/launchdarkly-java-server-sdk/latest/com/launchdarkly/sdk/server/LDConfig.Builder.html
    LDConfig config = new LDConfig.Builder()
      .http(
        Components
          .httpConfiguration()
          .connectTimeout(properties.getConnectTimeout())
          .socketTimeout(properties.getSocketTimeout())
      )
      .serviceEndpoints(
        Components
          .serviceEndpoints()
          .streaming(properties.getStreamingEndpoint())
          .polling(properties.getPollingEndpoint())
          .events(properties.getEventsEndpoint())
      )
      .events(
        Components
          .sendEvents()
          .flushInterval(properties.getEventFlushInterval())
      )
      .startWait(properties.getStartWait())
      // ApplicationInfo
      .build();
    this.launchdarklyClient = new LDClient(properties.getSdkKey(), config);
    return this.launchdarklyClient;
  }

  @PreDestroy
  public void destroy() throws IOException {
    this.launchdarklyClient.close();
  }
}
