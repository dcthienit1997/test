package com.sbc.bbx.sample.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@Document(collection = "samples")
public class SampleDocument {

  @Id
  private String id;

  @Version
  private Integer version;

  @Indexed(unique = true)
  private int sampleId;

  private String name;
  private int weight;

  public SampleDocument() {}

  public SampleDocument(int sampleId, String name, int weight) {
    this.sampleId = sampleId;
    this.name = name;
    this.weight = weight;
  }
}
