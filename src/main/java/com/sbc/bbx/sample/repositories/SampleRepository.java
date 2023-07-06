package com.sbc.bbx.sample.repositories;

import com.sbc.bbx.sample.models.SampleDocument;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface SampleRepository
  extends ReactiveCrudRepository<SampleDocument, String> {
  Flux<SampleDocument> findByNameContaining(String name);
}
