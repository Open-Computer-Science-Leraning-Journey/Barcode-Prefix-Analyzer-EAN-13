package com.peregrine.model;

public class BarcodeResponse {
  private final String country;

  public BarcodeResponse(String country, boolean isValid) {
    this.country = country;
  }

  public String getCountry() {
    return this.country;
  }
}
