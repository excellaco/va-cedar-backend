package com.excella.reactor.infrastructure.repositories;

import com.excella.reactor.domain.entities.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** An User repository */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  /** Get a User by username */
  Optional<User> findByUsername(String username);
}
