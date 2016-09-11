package org.pgramatictesting;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
  @GetMapping("/greet/{name}")
  public GreetResult greet(@PathVariable String name) {
    return GreetResult.builder()
        .name(name)
        .say("Hello " + name)
        .build();
  }

  @Data
  @Builder
  static class GreetResult {
    String name;
    String say;
  }
}
