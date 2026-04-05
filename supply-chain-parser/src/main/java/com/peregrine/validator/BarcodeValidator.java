package com.peregrine.validator;

import com.peregrine.model.Barcode;

public class BarcodeValidator {
  public boolean validate(Barcode barcode) {
    String value = barcode.getRawValue();

    // Câu lệnh điều kiện đọc từ trái sang phải, nếu đổi thứ tự, chương trình sẽ bị crash.
    // Lỗi này có tên cụ thể là NULLPointerException
    // Đây gọi là Short-circuit evaluation.
    // Với toán tử `||` nếu vế trái là `true` thì vế phải không được evaluate.
    // Đối với toán tử `&&` nêu vế trái là `false` thì vế phải không được evaluate
    if (value == null || value.length() != 13) {
      return false;
    }

    int sum = 0;
    for (int i = 0; i < 12; ++i) {
      int digit = Integer.parseInt(String.valueOf(value.charAt(i)));
      sum += (i % 2 == 0) ? digit : digit * 3;
    }

    int checkDigit = (10 - (sum % 10)) % 10; // Handle edge case
    int actualCheckDigit = Integer.parseInt(String.valueOf(value.charAt(12)));

    return checkDigit == actualCheckDigit;
  }
}
