package com.excella.reactor.application.service.impl;

import static org.mockito.Mockito.mock;

import com.excella.reactor.application.services.impl.UserDetailsServiceImpl;
import com.excella.reactor.domain.entities.User;
import com.excella.reactor.infrastructure.repositories.UserRepository;
import java.util.Optional;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@SuppressWarnings("PMD.UnusedPrivateMethod")
public class UserDetailsServiceImplUnitTests {
  private UserRepository userRepository;
  private UserDetailsServiceImpl userDetailsService;

  private User user;

  @BeforeClass
  private void beforeAll() {
    user = new User();

    user.setUsername("user"); // NOPMD
    user.setPassword("123");
  }

  @BeforeMethod
  private void beforeEach() {
    userRepository = mock(UserRepository.class);
    userDetailsService = new UserDetailsServiceImpl(userRepository);
  }

  @Test(description = "Should successfully load username")
  public void loadUsernameSuccess() {
    Mockito.when(userRepository.findByUsername("user")).thenReturn(Optional.of(user));
    var testUser = userDetailsService.loadUserByUsername("user");
    assert testUser.equals(user);
  }

  @Test(
      description = "Exception should be thrown if username cannot be found",
      expectedExceptions = UsernameNotFoundException.class)
  public void loadUsernameFailure() {
    Mockito.when(userRepository.findByUsername("user")).thenReturn(Optional.empty());
    userDetailsService.loadUserByUsername("user");
  }
}
