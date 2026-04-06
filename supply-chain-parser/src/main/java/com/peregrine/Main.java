package com.peregrine;

import com.peregrine.model.Barcode;
import com.peregrine.service.GS1PrefixService;
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

    Barcode barcode = new Barcode("40063813339AB");
    System.out.println("Test have character (expect false): " + validator.validate(barcode));

    GS1PrefixService gs1 = new GS1PrefixService();
    System.out.println("893... -> " + gs1.lookup("8931234567890"));
    System.out.println("400... -> " + gs1.lookup("4006381333931"));
    System.out.println("690... -> " + gs1.lookup("6901234567890"));
  }
}
