package com.example.demo.interceptors;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.io.IOException;

@Slf4j
@ControllerAdvice
public class ResponseInterceptor implements ResponseBodyAdvice<Object> {

  private static final String JSON = "json";

  @Override
  public boolean supports(final MethodParameter returnType, final Class<? extends HttpMessageConverter<?>> converterType) {
    return true;
  }

  @Override
  public Object beforeBodyWrite(final Object body, final MethodParameter returnType, final MediaType selectedContentType,
                                final Class<? extends HttpMessageConverter<?>> selectedConverterType, final ServerHttpRequest request,
                                final ServerHttpResponse response) {
    if (body != null && JSON.equals(selectedContentType.getSubtype())) {
      log.info("Response Body: {}", getJsonFromObject(body));
    }
    return body;
  }

  private String getJsonFromObject(final Object object) {
    final ObjectMapper Obj = new ObjectMapper();
    String json = null;
    try {
      json = Obj.writeValueAsString(object);
    } catch (IOException e) {
      log.info("ContentType is not a valid application/json.");
    }
    return json;
  }

}
