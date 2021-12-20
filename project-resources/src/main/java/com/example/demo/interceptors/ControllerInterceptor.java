package com.example.demo.interceptors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

@Slf4j
@Component
public class ControllerInterceptor implements AsyncHandlerInterceptor {

  @Override
  public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) {
    this.logRequest(request);
    return true;
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    log.info("Response Status: {}", response.getStatus());
    AsyncHandlerInterceptor.super.afterCompletion(request, response, handler, ex);
  }

  private void logRequest(final HttpServletRequest request) {
    log.info("[" + request.getMethod() + "]" + request.getRequestURI() + getParameters(request));
  }

  private String getParameters(final HttpServletRequest request) {
    final StringBuilder posted = new StringBuilder();
    final Enumeration<?> e = request.getParameterNames();
    if (e != null) {
      posted.append("?");
      while (e.hasMoreElements()) {
        if (posted.length() > 1) {
          posted.append("&");
        }
        final String curr = (String) e.nextElement();
        posted.append(curr).append("=");
        if (curr.contains("password")
                || curr.contains("pass")
                || curr.contains("pwd")) {
          posted.append("*****");
        } else {
          posted.append(request.getParameter(curr));
        }
      }
    }
    return posted.toString();
  }
}
