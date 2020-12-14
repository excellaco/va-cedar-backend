package com.excella.reactor.common.exceptions;

/** Throw when a resource is not found */
public class ResourceNotFoundException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  /** @param message */
  public ResourceNotFoundException(final String message) {
    super(message);
  }

  public static ResourceNotFoundException of(String message) { // NOPMD
    return new ResourceNotFoundException(message);
  }
}
