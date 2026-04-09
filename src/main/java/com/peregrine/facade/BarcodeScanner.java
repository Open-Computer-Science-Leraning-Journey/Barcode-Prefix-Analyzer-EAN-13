package com.peregrine.facade;

import com.peregrine.model.Barcode;
import com.peregrine.model.BarcodeResponse;
import com.peregrine.processor.BarcodeProcessor;

public class BarcodeScanner {
  private final BarcodeProcessor barcodeProcessor;

  public BarcodeScanner(BarcodeProcessor barcodeProcessor) {
    this.barcodeProcessor = barcodeProcessor;
  }

  public String scan(String userInput) {
    Barcode barcode = new Barcode(userInput);
    BarcodeResponse barcodeResponse = barcodeProcessor.process(barcode);

    return String.format(
        "Barcode: %s\nCountry: %s", barcode.getRawValue(), barcodeResponse.getCountry());
  }
}
