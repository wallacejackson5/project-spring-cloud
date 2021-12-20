package com.example.demo.config;

import com.example.demo.constants.ProfileConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
@Profile({ProfileConstants.PROFILE_UNSAFE})
public class WebFluxSecurityLocalConfig {

  @Bean
  public SecurityWebFilterChain securityWebFilterChain(final ServerHttpSecurity http) {
    http.authorizeExchange().anyExchange().permitAll();
    return http.build();
  }

}
