package com.example.demo.config;

import com.example.demo.constants.ProfileConstants;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Profile({ProfileConstants.PROFILE_UNSAFE})
@Configuration
public class WebSecurityLocalConfigTest extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(final HttpSecurity http) throws Exception {
    http.authorizeRequests().anyRequest().permitAll();
  }

}
