package com.excella.reactor.config;

import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/** Web security configuration */
@EnableWebSecurity
@Getter
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

  private PasswordEncoder passwordEncoder;
  private final UserDetailsService userDetailsService;

  /**
   * Create instance with UserDetailsService object
   *
   * @param userDetailsService
   */
  public WebSecurityConfiguration(final UserDetailsService userDetailsService) {
    this.userDetailsService = userDetailsService;
  }

  /**
   * Sets up Authentication with a UserDetailsService and PasswordEncoder.
   *
   * @see #userDetailsService()
   * @see #getPasswordEncoder()
   * @param auth AuthenticationManagerBuider
   * @throws Exception exception
   */
  @Override
  protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(getPasswordEncoder());
  }

  /**
   * Returns the default implementation of the AuthenticationManager and creates a bean from it.
   *
   * @return AuthenticationManager
   * @throws Exception exception
   */
  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  /**
   * Creates a password encoder.
   *
   * @return PasswordEncoder
   */
  @Bean(name = "passwordEncoder")
  public PasswordEncoder getPasswordEncoder() {
    if (passwordEncoder == null) {
      passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    return passwordEncoder;
  }
}
