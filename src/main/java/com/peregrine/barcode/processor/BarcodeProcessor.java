package com.peregrine.barcode.processor;

import com.peregrine.barcode.model.Barcode;
import com.peregrine.barcode.model.BarcodeResponse;
import com.peregrine.barcode.service.GS1PrefixService;
import com.peregrine.barcode.validator.BarcodeValidator;

public class BarcodeProcessor {
  private final BarcodeValidator barcodeValidator;
  private final GS1PrefixService gs1PrefixService;

  public BarcodeProcessor(BarcodeValidator barcodeValidator, GS1PrefixService gs1PrefixService) {
    this.barcodeValidator = barcodeValidator;
    this.gs1PrefixService = gs1PrefixService;
  }

  public BarcodeResponse process(Barcode barcode) {
    if (barcodeValidator.validate(barcode)) {
      return new BarcodeResponse(gs1PrefixService.lookup(barcode.getRawValue()));
    }
    return new BarcodeResponse("Unknown");
  }
}
