package com.excella.reactor.validation;

import java.util.stream.Stream;
import org.springframework.validation.ObjectError;

/** Interface for a rule that validates any object of type T. */
public interface ValidationRule<T> {

  /**
   * Implementing classes should return a stream of Spring ObjectErrors for every violation. If
   * there are no violations, an empty stream should be returned.
   *
   * @param target
   * @return stream of errors
   */
  Stream<ObjectError> validate(T target);

  /**
   * Convenience method for an empty stream.
   *
   * @return empty Stream
   */
  static Stream<ObjectError> noErrors() {
    return Stream.empty();
  }
}
