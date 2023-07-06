package com.sbc.bbx.sample.messaging;

import com.sbc.bbx.sample.exceptions.EventProcessingException;
import com.sbc.bbx.sample.models.Event;
import com.sbc.bbx.sample.models.Sample;
import java.util.function.Consumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class SampleConsumerConfiguration {

  @Bean
  public Consumer<Event<Integer, Sample>> sampleConsumer() {
    return event -> {
      log.info("Process message created at {}...", event.getEventCreatedAt());

      switch (event.getEventType()) {
        case CREATE:
          Sample sample = event.getData();
          log.info("Create sample with ID: {}", sample.getSampleId());
          break;
        case DELETE:
          int sampleId = event.getKey();
          log.info("Delete sample with sampleId: {}", sampleId);
          break;
        default:
          String errorMessage =
            "Incorrect event type: " +
            event.getEventType() +
            ", expected a CREATE or DELETE event";
          log.warn(errorMessage);
          throw new EventProcessingException(errorMessage);
      }

      log.info("Message processing done!");
    };
  }
}
