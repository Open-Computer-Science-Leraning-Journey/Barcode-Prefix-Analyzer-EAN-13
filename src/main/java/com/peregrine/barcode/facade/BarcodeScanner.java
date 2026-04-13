package com.peregrine.barcode.facade;

import com.peregrine.barcode.model.Barcode;
import com.peregrine.barcode.model.BarcodeResponse;
import com.peregrine.barcode.processor.BarcodeProcessor;

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
