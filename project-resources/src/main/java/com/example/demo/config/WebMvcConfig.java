package com.example.demo.config;

import com.example.demo.interceptors.ControllerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

  private final ControllerInterceptor controllerInterceptor;

  @Autowired
  public WebMvcConfig(final ControllerInterceptor controllerInterceptor) {
    this.controllerInterceptor = controllerInterceptor;
  }

  @Override
  protected void addInterceptors(final InterceptorRegistry registry) {
    registry.addInterceptor(controllerInterceptor);
  }
}
