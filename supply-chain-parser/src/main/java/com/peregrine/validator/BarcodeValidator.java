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

    // thứ tự check còn quyết định về hiệu năng
    // fail fast - loại bỏ trường hợp sai càng sớm càng tốt.
    if (value == null) { // chi phí hiệu năng rẻ nhất
      return false;
    }

    if (value.length() != 13) { // chi phí rẻ
      return false;
    }

    // regex phải compiler pattern, scan toàn chuỗi
    if (!value.matches("\\d{13}")) { // chi phí đắt nhất
      // `//1` đúng 1 ký tự (0-9)
      // {13} lặp lại 13 lần
      return false;
    }

    // Patern này có tên: Gaurd Clause xử lý hết trường hợp invalid ở đầu method, return sớm. phần
    // còn lại của method chỉ xử lý lý happy path.

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
