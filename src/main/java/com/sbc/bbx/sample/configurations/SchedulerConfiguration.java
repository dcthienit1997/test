package com.sbc.bbx.sample.configurations;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

@Configuration
@Slf4j
public class SchedulerConfiguration {

  @Bean
  public Scheduler publishEventScheduler(EventSchedulerProperties properties) {
    log.info(
      "Creates a messagingScheduler with connectionPoolSize = {}",
      properties.getThreadPoolSize()
    );
    return Schedulers.newBoundedElastic(
      properties.getThreadPoolSize(),
      properties.getTaskQueueSize(),
      "publish-pool"
    );
  }
}
