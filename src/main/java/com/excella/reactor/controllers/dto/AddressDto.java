package com.excella.reactor.controllers.dto;

import lombok.Value;

/** JSON Representation of an Address */
@Value
public class AddressDto {

  String line1;
  String line2;
  String city;
  String stateCode;
  String zipCode;
}
