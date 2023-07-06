package com.sbc.bbx.sample.connectors;

import static java.util.logging.Level.FINE;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

import com.sbc.bbx.sample.exceptions.InvalidInputException;
import com.sbc.bbx.sample.exceptions.NotFoundException;
import java.net.URI;
import java.util.Collections;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class SampleConnector {

  @Autowired
  private WebClient webClient;

  public Mono<String> testResourceServer(int sampleId) {
    URI url = UriComponentsBuilder
      .fromUriString("http://localhost:8080/api/samples/{id}")
      .build(Collections.singletonMap("id", sampleId));
    log.debug("Will call the resource server API on URL: {}", url);

    return webClient
      .get()
      .uri(url)
      .retrieve()
      .bodyToMono(String.class)
      .log(log.getName(), FINE)
      .onErrorMap(WebClientResponseException.class, this::handleException);
  }

  private Throwable handleException(Throwable ex) {
    if (!(ex instanceof WebClientResponseException wcre)) {
      log.warn("Got a unexpected error: {}, will rethrow it", ex.toString());
      return ex;
    }
    HttpStatusCode statusCode = wcre.getStatusCode();
    if (NOT_FOUND.equals(statusCode)) {
      return new NotFoundException(wcre.getMessage());
    } else if (UNPROCESSABLE_ENTITY.equals(statusCode)) {
      return new InvalidInputException(wcre.getMessage());
    }
    log.warn(
      "Got an unexpected HTTP error: {}, will rethrow it",
      wcre.getStatusCode()
    );
    log.warn("Error body: {}", wcre.getResponseBodyAsString());
    return ex;
  }
}
