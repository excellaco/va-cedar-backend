package com.excella.reactor.controllers.validators;

import static com.excella.reactor.validation.ValidationRuleUtils.mustMeetRegex;

import com.excella.reactor.controllers.dto.AddressDto;
import com.excella.reactor.validation.ValidationRule;
import com.excella.reactor.validation.ValidationRuleSet;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import org.springframework.validation.*;

/** Validation Ruleset for the JSON representation of an address */
public class AddressDtoValidator extends ValidationRuleSet<AddressDto> {

  /** Creates pre-defined validation ruleset */
  private AddressDtoValidator() {
    this.rules = Stream.<ValidationRule<AddressDto>>builder().add(validZip()).build();
  }

  /**
   * Static constructor for convenience.
   *
   * @return a new validation rulesset
   */
  public static AddressDtoValidator create() {
    return new AddressDtoValidator();
  }

  private static ValidationRule<AddressDto> validZip() {
    return dto -> {
      final ObjectError zipError =
          new ObjectError(AddressDto.class.getName(), "ZIP must be 5 digits");

      final Pattern exactlyFiveDigits = Pattern.compile("^\\d{5}$");

      return mustMeetRegex(exactlyFiveDigits, zipError).validate(dto.getZipCode());
    };
  }
}
