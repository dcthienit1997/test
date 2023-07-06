package com.sbc.bbx.sample.services;

import com.sbc.bbx.sample.models.Sample;
import com.sbc.bbx.sample.models.SampleDocument;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SampleMapper {
  Sample documentToApi(SampleDocument document);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "version", ignore = true)
  SampleDocument apiToDocument(Sample api);

  default SampleDocument mergeApiIntoDocument(
    Sample api,
    @MappingTarget SampleDocument target
  ) {
    target.setName(api.getName());
    target.setSampleId(api.getSampleId());
    target.setWeight(api.getWeight());
    return target;
  }
}
