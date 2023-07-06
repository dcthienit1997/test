package com.sbc.bbx.sample.messaging;

import com.sbc.bbx.sample.models.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SampleProducer {

  @Autowired
  private StreamBridge streamBridge;

  public <K, T> void sendMessage(String bindingName, Event<K, T> event) {
    log.debug("Sending a {} message to {}", event.getEventType(), bindingName);
    Message<Event<K, T>> message = MessageBuilder
      .withPayload(event)
      .setHeader("partitionKey", event.getKey())
      .build();
    streamBridge.send(bindingName, message);
  }
}
