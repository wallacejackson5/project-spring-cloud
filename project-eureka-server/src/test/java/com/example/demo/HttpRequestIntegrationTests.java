package com.example.demo;

import com.example.demo.config.BaseIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HttpRequestIntegrationTests extends BaseIntegrationTest {

  final private int port;
  final private TestRestTemplate restTemplate;

  @Autowired
  public HttpRequestIntegrationTests(@LocalServerPort final int port, final TestRestTemplate restTemplate) {
    this.port = port;
    this.restTemplate = restTemplate;
  }

  @Test
  public void requestShouldReturnSuccessfully() {
    final HttpEntity<Object> entity = new HttpEntity<>(new HttpHeaders());
    final String url = "http://localhost:" + port + "/";
    final ResponseEntity<String> out = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

    assertThat(out.getStatusCode()).isEqualTo(HttpStatus.OK);
  }
}