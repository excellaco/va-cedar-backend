package com.excella.reactor.application.services.impl;

import com.excella.reactor.infrastructure.repositories.UserRepository;
import lombok.Getter;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/** User Details service implementation */
@Service
@Primary
@Getter
public class UserDetailsServiceImpl implements UserDetailsService {
  private final UserRepository userRepository;

  /**
   * Creates a instance with UserRepository object
   *
   * @param userRepository a UserRepository object
   */
  public UserDetailsServiceImpl(final UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  /**
   * Gets UserDetails object by username if exists, else exception thrown
   *
   * @param username a username to find
   * @return a UserDetails object
   */
  @Override
  public UserDetails loadUserByUsername(final String username) {
    return userRepository
        .findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("username not found"));
  }
}
