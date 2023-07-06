package com.sbc.bbx.sample.controllers;

import com.sbc.bbx.sample.apis.SampleApi;
import com.sbc.bbx.sample.models.Sample;
import com.sbc.bbx.sample.services.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
public class SampleController implements SampleApi {

  @Autowired
  SampleService sampleService;

  @Override
  public Mono<Sample> createSample(
    Mono<Sample> sample,
    ServerWebExchange exchange
  ) {
    return sample.flatMap(data -> sampleService.save(data));
  }

  @Override
  public Mono<Void> deleteSample(String id, ServerWebExchange exchange) {
    return sampleService.deleteById(id);
  }

  @Override
  public Mono<Sample> getSample(String id, ServerWebExchange exchange) {
    return sampleService.findById(id);
  }

  @Override
  public Mono<Sample> updateSample(
    String id,
    Mono<Sample> sample,
    ServerWebExchange exchange
  ) {
    return sample.flatMap(data -> sampleService.update(id, data));
  }
}
