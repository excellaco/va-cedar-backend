package com.excella.reactor.validation;

import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import org.springframework.validation.ObjectError;

/**
 * Utility class for generically applicable validation rules. Rules here should only be for general
 * Java classes, e.g. String, Integer, etc.
 */
public class ValidationRuleUtils {

  /**
   * Creates a string validation rule based on a regex pattern. If the provided string does not meet
   * the pattern (including being null) it will return the supplied error in a stream
   *
   * @param regex Pattern to validate
   * @param error Error to be thrown
   * @return Constructed Validation Rule
   */
  public static ValidationRule<String> mustMeetRegex(final Pattern regex, final ObjectError error) {
    return str -> {
      if (Optional.ofNullable(str).filter(regex.asMatchPredicate()).isEmpty()) {
        return Stream.of(error);
      } else {
        return Stream.empty();
      }
    };
  }
}
