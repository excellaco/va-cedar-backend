package com.excella.reactor.domain.entities;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Data;

/** Contact information including address, email, and phone number */
@Data
@Embeddable
public class Contact implements Serializable {
  private static final long serialVersionUID = 1L;

  @NotEmpty @Email private String email;

  @NotNull
  @Pattern(
      regexp = "\\(\\d{3}\\)\\d{3}-\\d{4}",
      message = "Phone number must be of form (xxx)xxx-xxxx")
  private String phoneNumber;

  @Embedded @Valid @NotNull private Address address;
}
