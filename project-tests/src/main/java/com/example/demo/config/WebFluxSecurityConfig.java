package com.example.demo.config;

import com.example.demo.constants.ProfileConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Profile({ProfileConstants.PROFILE_SAFE})
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class WebFluxSecurityConfig {

  @Bean
  public SecurityWebFilterChain securityWebFilterChain(final ServerHttpSecurity http) {
    http.authorizeExchange().anyExchange().authenticated().and().oauth2ResourceServer().jwt();
    return http.build();
  }

}
