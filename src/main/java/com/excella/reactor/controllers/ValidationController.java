package com.excella.reactor.controllers;

import static java.util.stream.Collectors.toList;

import com.excella.reactor.controllers.dto.AddressDto;
import com.excella.reactor.controllers.mappers.AddressDtoToAddress;
import com.excella.reactor.controllers.validators.AddressDtoValidator;
import com.excella.reactor.validation.ValidationRuleSetException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.MimeTypeUtils;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/** A controller for Employees */
@RestController
@RequestMapping("/validate")
@Slf4j
public class ValidationController {

  private static final String JSON = MimeTypeUtils.APPLICATION_JSON_VALUE;

  /**
   * validates an address format
   *
   * @param addressDto address to validate
   * @return Mono providing the result
   */
  @PostMapping(value = "/address", name = "validate an address", produces = JSON)
  public Mono<String> validateAddress(final @RequestBody AddressDto addressDto) {

    final List<ObjectError> validationErrors =
        AddressDtoValidator.create().performValidation(addressDto).collect(toList());
    if (!validationErrors.isEmpty()) {
      return Mono.error(() -> new ValidationRuleSetException(validationErrors));
    }

    final var convert = new AddressDtoToAddress();
    convert.apply(addressDto);

    return Mono.just("Valid");
  }
}
