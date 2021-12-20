package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/fallback")
public class FallbackController {

  @PostMapping(value = "/default")
  private Mono<ResponseEntity<String>> fallback() {
    return Mono.create(s -> s.success(ResponseEntity.ok("I'm fallback!")));
  }

}
