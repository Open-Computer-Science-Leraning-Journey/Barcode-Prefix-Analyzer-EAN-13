package com.peregrine.model;

// Dữ liệu thô, nó không cần bản thân nó đúng hay không
// Pure data object
public class Barcode {
  private final String rawValue;

  public Barcode(String rawValue) {
    this.rawValue = rawValue == null ? null : rawValue.trim();
  }

  public String getRawValue() {
    return this.rawValue;
  }
}
