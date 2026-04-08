package com.peregrine;

import com.peregrine.model.Barcode;
import com.peregrine.model.BarcodeResponse;
import com.peregrine.processor.BarcodeProcessor;
import com.peregrine.service.GS1PrefixService;
import com.peregrine.validator.BarcodeValidator;

public class Main {
  public static void main(String[] args) {
    Barcode barcodeFake = new Barcode("8931234567890");
    Barcode barcodeAuth = new Barcode("4006381333931");
    BarcodeValidator barcodeValidator = new BarcodeValidator();
    GS1PrefixService gs1PrefixService = new GS1PrefixService();
    BarcodeProcessor barcodeProcessor = new BarcodeProcessor(barcodeValidator, gs1PrefixService);
    BarcodeResponse response = barcodeProcessor.process(barcodeFake);
    System.out.println("Barcode: " + barcodeFake.getRawValue());
    System.out.println("Authentication: " + response.isValid());
    System.out.println("Country: " + response.getCountry());

    BarcodeResponse response2 = barcodeProcessor.process(barcodeAuth);
    System.out.println("Barcode: " + barcodeAuth.getRawValue());
    System.out.println("Authentication: " + response2.isValid());
    System.out.println("Country: " + response2.getCountry());
  }
}
