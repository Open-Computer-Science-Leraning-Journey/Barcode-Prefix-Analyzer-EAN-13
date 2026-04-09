package com.peregrine.model;

public class BarcodeResponse {
  private final String country;

  public BarcodeResponse(String country) {
    this.country = country;
  }

  public String getCountry() {
    return this.country;
  }
}
