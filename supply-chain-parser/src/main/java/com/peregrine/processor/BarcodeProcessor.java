package com.peregrine.processor;

import com.peregrine.model.Barcode;
import com.peregrine.model.BarcodeResponse;
import com.peregrine.service.GS1PrefixService;
import com.peregrine.validator.BarcodeValidator;

public class BarcodeProcessor {
  private final BarcodeValidator barcodeValidator;
  private final GS1PrefixService gs1PrefixService;

  public BarcodeProcessor(BarcodeValidator barcodeValidator, GS1PrefixService gs1PrefixService) {
    this.barcodeValidator = barcodeValidator;
    this.gs1PrefixService = gs1PrefixService;
  }

  public BarcodeResponse process(Barcode barcode) {
    boolean isValid = barcodeValidator.validate(barcode);
    if (isValid) {
      String country = gs1PrefixService.lookup(barcode.getRawValue());
      return new BarcodeResponse(country, isValid);
    }
    return new BarcodeResponse("Unknown", isValid);
  }
}
