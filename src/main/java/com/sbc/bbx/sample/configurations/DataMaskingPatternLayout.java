package com.sbc.bbx.sample.configurations;

import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.spi.ILoggingEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class DataMaskingPatternLayout extends PatternLayout {

  private Pattern aplpliedPattern;
  private final List<String> maskPatterns = new ArrayList<>();

  public void addMaskPattern(String maskPattern) {
    maskPatterns.add(maskPattern);
    aplpliedPattern =
      Pattern.compile(String.join("|", maskPatterns), Pattern.MULTILINE);
  }

  @Override
  public String doLayout(ILoggingEvent event) {
    return maskMessage(super.doLayout(event));
  }

  private String maskMessage(String message) {
    //When masking is disabled in a environment
    if (aplpliedPattern == null) {
      return message;
    }
    StringBuilder sb = new StringBuilder(message);
    Matcher matcher = aplpliedPattern.matcher(sb);
    while (matcher.find()) {
      IntStream
        .rangeClosed(1, matcher.groupCount())
        .forEach(group -> {
          if (matcher.group(group) != null) {
            IntStream
              .range(matcher.start(group), matcher.end(group))
              .forEach(i -> sb.setCharAt(i, '*'));
          }
        });
    }
    return sb.toString();
  }
}
