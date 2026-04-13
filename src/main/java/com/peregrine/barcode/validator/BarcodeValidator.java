package com.peregrine.barcode.validator;

import com.peregrine.barcode.model.Barcode;

public class BarcodeValidator {
  public boolean validate(Barcode barcode) {
    if (barcode == null) {
      return false;
    }

    String value = barcode.getRawValue();

    if (value == null) {
      return false;
    }

    if (value.length() != 13) {
      return false;
    }

    if (!value.matches("\\d{13}")) {
      return false;
    }

    int sum = 0;
    for (int i = 0; i < 12; ++i) {
      int digit = Integer.parseInt(String.valueOf(value.charAt(i)));
      sum += (i % 2 == 0) ? digit : digit * 3;
    }

    int checkDigit = (10 - (sum % 10)) % 10;
    int actualCheckDigit = Integer.parseInt(String.valueOf(value.charAt(12)));

    return checkDigit == actualCheckDigit;
  }
}
