package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HttpRequestIntegrationTests {

    final private int port;
    final private TestRestTemplate restTemplate;

    @Autowired
    public HttpRequestIntegrationTests(@LocalServerPort final int port, final TestRestTemplate restTemplate) {
        this.port = port;
        this.restTemplate = restTemplate;
    }

    @Test
    public void requestShouldReturnSuccessfully() {
        final String url = "http://localhost:"
                + port + "/project-config-server/actuator/health";

        assertThat(this.restTemplate.getForObject(url,
                String.class)).contains("{\"status\":\"UP\"}");
    }
}
