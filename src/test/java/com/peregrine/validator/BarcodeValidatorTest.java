package com.peregrine.validator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.peregrine.model.Barcode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BarcodeValidatorTest {
  private BarcodeValidator validator;

  @BeforeEach
  // BeforeEach đảo bảo mỗi test nhận một `validator` mới tinh, trạng thái sạch. Nếu
  // `BarcodeValidator` có trạng thái riêng bên trong thì test trước sẽ làm bẩn test sau.

  // Đây là nguyên tắc Test Isolation. Tại sao pass test riêng lẻ nhưng fail khi chạy cùng nhau, vi
  // phạm nguyên tắc.
  void setup() {
    validator = new BarcodeValidator();
  }

  @Test
  void shouldReturnTrueWhenCheckDigitIsValid() {
    Barcode barcode = new Barcode("4006381333931");
    assertTrue(validator.validate(barcode));
  }

  @Test
  void shouldReturnFalseWhenCheckDigitIsWrong() {
    Barcode barcode = new Barcode("4006381333932");
    assertFalse(validator.validate(barcode));
  }

  @Test
  void shouldReturnFalseWhenBarcodeIsNull() {
    Barcode barcode = new Barcode(null);
    assertFalse(validator.validate(barcode));
  }

  @Test
  void shouldReturnFalseWhenBarcodeContainsLetters() {
    Barcode barcode = new Barcode("40063813339AB");
    assertFalse(validator.validate(barcode));
  }
}
