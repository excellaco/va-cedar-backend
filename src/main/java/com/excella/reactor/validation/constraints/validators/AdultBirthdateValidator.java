package com.excella.reactor.validation.constraints.validators;

import com.excella.reactor.validation.constraints.AdultBirthdate;
import java.time.LocalDate;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/** Validates an Adult's birthday */
public class AdultBirthdateValidator implements ConstraintValidator<AdultBirthdate, LocalDate> {
  private int ageOfMajority;

  /**
   * Initializes the object
   *
   * @param annotation an AdultBirthdate annotation
   */
  @Override
  public void initialize(final AdultBirthdate annotation) {
    this.ageOfMajority = annotation.ageOfMajority();
  }

  /**
   * Validates an adult birth date is valid
   *
   * @param value a birth date
   * @param context a validator context
   * @return true if valid birth date; otherwise, false
   */
  @Override
  public boolean isValid(final LocalDate value, final ConstraintValidatorContext context) {
    return value == null || value.plusYears(ageOfMajority).minusDays(1).isBefore(LocalDate.now());
  }
}
