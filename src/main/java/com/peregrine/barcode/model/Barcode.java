package com.peregrine.barcode.model;

public class Barcode {
  private final String rawValue;

  public Barcode(String rawValue) {
    this.rawValue = rawValue == null ? null : rawValue.trim();
  }

  public String getRawValue() {
    return this.rawValue;
  }
}
