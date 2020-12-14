package com.excella.reactor.validation;

import java.util.stream.Stream;
import org.springframework.validation.ObjectError;

/**
 * Parent class for a collection of validation rules. Generally there should be one ruleset for each
 * class that requires validation * @param <T>
 */
public class ValidationRuleSet<T> {

  protected Stream<ValidationRule<T>> rules;

  /**
   * Generic method for executing all the validation rules and returning a stream of any and all
   * errors found by those validation rules
   *
   * @param target
   * @return
   */
  public Stream<ObjectError> performValidation(final T target) {
    return rules.flatMap(rule -> rule.validate(target));
  }
}
