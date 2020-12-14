package com.excella.reactor.controllers.mappers;

import com.excella.reactor.controllers.dto.AddressDto;
import com.excella.reactor.domain.entities.Address;
import java.util.function.Function;

/** Mapper for converting a JSON representation of an Address into the domain object */
public class AddressDtoToAddress implements Function<AddressDto, Address> {
  @Override
  public Address apply(final AddressDto dto) {
    final Address mapped = new Address();

    mapped.setLine1(dto.getLine1());
    mapped.setLine2(dto.getLine2());
    mapped.setCity(dto.getCity());
    mapped.setStateCode(dto.getStateCode());
    mapped.setZipCode(dto.getZipCode());
    return mapped;
  }
}
