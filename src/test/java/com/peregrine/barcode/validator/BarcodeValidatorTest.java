package com.peregrine.barcode.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.peregrine.barcode.service.GS1PrefixService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class BarcodeValidatorTest {
  private GS1PrefixService service;

  @BeforeEach
  void setup() {
    service = new GS1PrefixService();
  }

  @ParameterizedTest(name = "Input {0} should return {1}")
  @CsvSource({
    // Barcode chuẩn
    "8931234567890, Vietnam",
    "4006381333931, Germany",
    "6951234567890, China",
    "6970697963916, China",
    "4066755192793, Germany",
    "4099683071274, Germany",
    "4066749194970, Germany",
    "4513574031449, Japan",
    "4902750401594, Japan",
    "4792252001251, Sri Lanka",
    "8850393800679, Thailand",

    // Barcode sai, lỗi
    "0000000000000, Unknown",
    "89, Unknown"
  })
  void shouldReturnCorrectCountry(String barcode, String expectedCountry) {
    assertEquals(expectedCountry, service.lookup(barcode), () -> "Failed for barcode" + barcode);
  }

  @Test
  void shouldReturnUnknownWhenInputIsNull() {
    assertEquals("Unknown", service.lookup(null));
  }
}
