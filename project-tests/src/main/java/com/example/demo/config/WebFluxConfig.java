package com.example.demo.config;

import com.example.demo.interceptors.ResponseInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.RequestPath;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class WebFluxConfig implements WebFilter {

  @Override
  public Mono<Void> filter(final ServerWebExchange serverWebExchange,
                           final WebFilterChain webFilterChain) {
    logRequest(serverWebExchange);
    return webFilterChain.filter(serverWebExchange.mutate()
            .response(new ResponseInterceptor(serverWebExchange.getResponse(), serverWebExchange.getResponse().getStatusCode())).build());
  }

  private void logRequest(final ServerWebExchange serverWebExchange) {
    final RequestPath path = serverWebExchange.getRequest().getPath();
    final String params = getParameters(serverWebExchange.getRequest().getQueryParams());
    log.info("[" + serverWebExchange.getRequest().getMethod().name() + "]" + path.toString() + params);
  }

  private String getParameters(final MultiValueMap<String, String> request) {
    final StringBuilder posted = new StringBuilder();
    posted.append("?");
    for (String key : request.keySet()) {
      final String value = request.get(key).get(0);

      posted.append(key).append("=");
      if (key.contains("password")
              || key.contains("pass")
              || key.contains("pwd")) {
        posted.append("*****");
      } else {
        posted.append(value);
      }
      posted.append("&");
    }
    return posted.toString();
  }
}
