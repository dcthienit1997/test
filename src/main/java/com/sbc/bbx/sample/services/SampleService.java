package com.sbc.bbx.sample.services;

import com.sbc.bbx.sample.models.Sample;
import com.sbc.bbx.sample.models.SampleDocument;
import com.sbc.bbx.sample.repositories.SampleRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class SampleService {

  @Autowired
  SampleRepository sampleRepository;

  @Autowired
  SampleMapper sampleMapper;

  public Mono<Sample> save(Sample sample) {
    SampleDocument document = sampleMapper.apiToDocument(sample);
    return sampleRepository
      .save(document)
      .flatMap(ent -> Mono.just(sampleMapper.documentToApi(ent)));
  }

  public Mono<Sample> findById(String id) {
    return sampleRepository
      .findById(id)
      .flatMap(document -> Mono.just(sampleMapper.documentToApi(document)));
  }

  public Mono<Sample> update(String id, Sample sample) {
    return sampleRepository
      .findById(id)
      .map(Optional::of)
      .defaultIfEmpty(Optional.empty())
      .flatMap(documentOpt -> {
        if (documentOpt.isPresent()) {
          SampleDocument savedSample = documentOpt.get();
          SampleDocument documentToUpdate = sampleMapper.mergeApiIntoDocument(
            sample,
            savedSample
          );
          return sampleRepository
            .save(documentToUpdate)
            .flatMap(document -> Mono.just(sampleMapper.documentToApi(document))
            );
        }
        return Mono.empty();
      });
  }

  public Mono<Void> deleteById(String id) {
    return sampleRepository.deleteById(id);
  }
}
