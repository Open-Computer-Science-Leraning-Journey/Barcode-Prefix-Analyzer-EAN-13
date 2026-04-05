package com.peregrine;

import com.peregrine.model.Barcode;
import com.peregrine.validator.BarcodeValidator;

public class Main {
  public static void main(String[] args) {
    BarcodeValidator validator = new BarcodeValidator();

    Barcode barcode1 = new Barcode("4006381333931");
    System.out.println("Test 1 (expect true) : " + validator.validate(barcode1));

    Barcode barcode2 = new Barcode("4006381333932");
    System.out.println("Test 2 (expect false): " + validator.validate(barcode2));

    Barcode barcode3 = new Barcode(null);
    System.out.println("Test 3 (expect false): " + validator.validate(barcode3));
  }
}
