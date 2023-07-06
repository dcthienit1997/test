package com.sbc.bbx.sample.configurations;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("event-scheduler")
public class EventSchedulerProperties {

  private Integer threadPoolSize = 10;

  private Integer taskQueueSize = 100;
}
