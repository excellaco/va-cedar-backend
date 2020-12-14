package com.excella.reactor.validation;

import java.util.List;
import javax.validation.ValidationException;
import lombok.Getter;
import org.springframework.validation.ObjectError;

/** Exception to be thrown by controllers when an input fails its validation rules */
public class ValidationRuleSetException extends ValidationException {

  private static final long serialVersionUID = 1L;

  @Getter private final List<ObjectError> errorList;

  /**
   * Sets Default Message, adds list of Errors
   *
   * @param errors
   */
  public ValidationRuleSetException(final List<ObjectError> errors) {
    super("Validation Rules Violated");
    errorList = errors;
  }
}
