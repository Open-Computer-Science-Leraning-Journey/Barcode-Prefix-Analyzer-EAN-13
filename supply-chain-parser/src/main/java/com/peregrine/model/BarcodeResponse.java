package com.peregrine.model;

public class BarcodeResponse {
  private final String country;
  private final boolean isValid;

  public BarcodeResponse(String country, boolean isValid) {
    this.country = country;
    this.isValid = isValid;
  }

  public String getCountry() {
    return this.country;
  }

  public boolean isValid() {
    return this.isValid;
  }
}
