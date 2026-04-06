package com.peregrine.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GS1PrefixServiceTest {
  private GS1PrefixService service;

  @BeforeEach
  void setup() {
    service = new GS1PrefixService();
  }

  @Test
  void shouldReturnVietNamWhenInputIs893() {
    String result = service.lookup("8931234567890");
    assertTrue(result.equals("Vietnam"));
  }

  @Test
  void shouldReturnGermanyWhenInputIs400() {
    String result = service.lookup("4006381333931");
    assertTrue(result.equals("Germany"));
  }

  @Test
  void shouldReturnChinaWhenInputIs695() {
    String result = service.lookup("6951234567890");
    assertTrue(result.equals("China"));
  }

  @Test
  void shouldReturnUnknownWhenInputIsNull() {
    String result = service.lookup(null);
    assertTrue(result.equals("Unknown"));
  }

  @Test
  void shouldReturnUnknownWhenInputIsLessThan13Character() {
    String result = service.lookup("89");
    assertTrue(result.equals("Unknown"));
  }

  @Test
  void shouldReturnUnknownWhenInputIsInvalid() {
    String result = service.lookup("000");
    assertTrue(result.equals("Unknown"));
  }
}
