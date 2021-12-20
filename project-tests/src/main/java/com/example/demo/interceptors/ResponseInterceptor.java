package com.example.demo.interceptors;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.reactivestreams.Publisher;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import reactor.core.publisher.Mono;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.channels.Channels;

@Slf4j
public class ResponseInterceptor extends ServerHttpResponseDecorator {

  private final int status;

  public ResponseInterceptor(final ServerHttpResponse delegate, HttpStatus status) {
    super(delegate);
    this.status = status == null ? 0 : status.value();
  }

  @Override
  public Mono<Void> writeWith(final Publisher<? extends DataBuffer> body) {
    final Mono<DataBuffer> buffer = Mono.from(body);
    return super.writeWith(buffer.doOnNext(dataBuffer -> {
      final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
      try {
        Channels.newChannel(byteArrayOutputStream).write(dataBuffer.asByteBuffer().asReadOnlyBuffer());
      } catch (final IOException e) {
        log.info("ContentType is not a valid application/json.");
      }
      final String responseBody = byteArrayOutputStream.toString();
      if (!StringUtils.isEmpty(responseBody)) {
        log.info("Response Body: {}", responseBody);
      }
      log.info("Response Status: {}", status);
    }));
  }
}