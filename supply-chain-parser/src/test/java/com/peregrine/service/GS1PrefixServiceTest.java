package com.peregrine.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GS1PrefixServiceTest {
  private GS1PrefixService service;

  @BeforeEach
  void setup() {
    service = new GS1PrefixService();
  }

  @Test
  void shouldReturnVietNamWhenPrefixIs893() {
    String result = service.lookup("8931234567890");
    assertEquals("Vietnam", result);
  }

  @Test
  void shouldReturnGermanyWhenPrefixIs400() {
    String result = service.lookup("4006381333931");
    assertEquals("Germany", result);
  }

  @Test
  void shouldReturnChinaWhenPrefixIs695() {
    String result = service.lookup("6951234567890");
    assertEquals("China", result);
  }

  @Test
  void shouldReturnUnknownWhenInputIsNull() {
    String result = service.lookup(null);
    assertEquals("Unknown", result);
  }

  @Test
  void shouldReturnUnknownWhenInputIsLessThan3Character() {
    String result = service.lookup("89");
    assertEquals("Unknown", result);
  }

  @Test
  void shouldReturnUnknownWhenInputIsInvalid() {
    String result = service.lookup("000");
    assertEquals("Unknown", result);
  }
}
