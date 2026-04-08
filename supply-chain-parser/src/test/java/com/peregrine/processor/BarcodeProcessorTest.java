package com.peregrine.processor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.peregrine.model.Barcode;
import com.peregrine.model.BarcodeResponse;
import com.peregrine.service.GS1PrefixService;
import com.peregrine.validator.BarcodeValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BarcodeProcessorTest {
  private BarcodeValidator barcodeValidator;
  private GS1PrefixService gs1PrefixService;
  private BarcodeProcessor barcodeProcessor;

  @BeforeEach
  void setup() {
    this.barcodeValidator = new BarcodeValidator();
    this.gs1PrefixService = new GS1PrefixService();

    this.barcodeProcessor = new BarcodeProcessor(barcodeValidator, gs1PrefixService);
  }

  @Test
  void shouldReturnUnknownWhenBarcodeContainsSpecialCharacter() {
    Barcode specialCharacterBarcode = new Barcode("\n\t\b");
    BarcodeResponse response = barcodeProcessor.process(specialCharacterBarcode);
    assertFalse(response.isValid());
    assertEquals("Unknown", response.getCountry());
  }

  @Test
  void shouldReturnFalseWhenBarcodeIsACommandInjection() {
    Barcode maliciousBarcode = new Barcode(";sudo rm -rf /");

    BarcodeResponse response = barcodeProcessor.process(maliciousBarcode);

    assertFalse(response.isValid());
    assertEquals("Unknown", response.getCountry());
  }

  @Test
  void shouldPassWhenBarcodeIsCorrect() {
    Barcode vnBarcode = new Barcode("8935049101109");
    BarcodeResponse response = barcodeProcessor.process(vnBarcode);

    assertTrue(response.isValid());
    assertEquals("Vietnam", response.getCountry());
  }

  @Test
  void shouldFailWhenBarcodeHasLeadingWhitespace() {
    Barcode newLine = new Barcode(" 8935049101109");
    BarcodeResponse response = barcodeProcessor.process(newLine);
    assertTrue(response.isValid());
    assertEquals("Vietnam", response.getCountry());
  }
}
