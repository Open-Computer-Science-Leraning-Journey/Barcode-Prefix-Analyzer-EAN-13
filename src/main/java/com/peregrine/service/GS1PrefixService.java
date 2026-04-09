package com.peregrine.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class GS1PrefixService {

  private final Map<String, String> prefixMap; // Không thể trỏ sang Object khác

  public GS1PrefixService() {
    prefixMap = new HashMap<>();
    loadPrefixes();
  }

  private void loadPrefixes() {
    InputStream is = getClass().getClassLoader().getResourceAsStream("gs1-prefixes.csv");
    // `getClass()`: lấy class hiện tại
    // `getClassLoader()`: lấy bộ nạp class, nó biết classpath ở đâu, nó chỉ cần biết tên file.
    // `getResourceAsStream`: mở file classpath như một luồng type

    // Nếu file không tồn tại, thuộc về lỗi cấu hình, không phải lỗi người dùng nên dùng try_catch
    if (is == null) {
      throw new RuntimeException("Cannot find gs1-prefixes.csv in classpath");
    }

    try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
      // Đây là cú pháp try-with-resource, Java sẽ tự động gọi .close() khi thoát khỏi block dù
      // thành công hay exception

      // resource leak là gì? Là mở file/connection mà không đóng lại. Tích lũy đủ nhiều sẽ làm hết
      // handle của OS. try-with-resource là cách mà Java enforce việc đóng resource tự động. Bất kỳ
      // class nào implement `AutoCloseable` đều dùng được cú pháp này

      // try-with-resource là cách Java giải quyết vấn đề resource leak từ Java 7
      reader.readLine(); // bỏ qua header

      String line;
      while ((line = reader.readLine()) != null) {
        String[] parts = line.split(",");
        int start = Integer.parseInt(parts[0]);
        int end = Integer.parseInt(parts[1]);
        String country = parts[2];

        for (int i = start; i <= end; i++) {
          prefixMap.put(String.valueOf(i), country);
        }
      }

    } // Java tự động gọi reader.close() ở đây.
    catch (IOException e) {
      // Exception là bắt toàn bộ lỗi.
      // IOException chỉ bắt lỗi IO file.
      // Catch quá rộng là anti-pattern. Nếu có bug không liên quan đến IO bên trong block,
      // Exception sẽ nuốt toàn bộ, rất khó debug
      throw new RuntimeException("Failed to load GS1 prefix data", e);
    }
  }

  public String lookup(String barcode) {
    if (barcode == null || barcode.length() < 3) {
      return "Unknown";
    }
    String prefix = barcode.substring(0, 3);
    String country = prefixMap.get(prefix);
    return country != null ? country : "Unknown";
  }
}
