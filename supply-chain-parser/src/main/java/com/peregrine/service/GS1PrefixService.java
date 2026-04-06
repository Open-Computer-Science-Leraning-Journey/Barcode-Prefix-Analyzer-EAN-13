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
    // `getClassLoader()`: lấy bộ nạp class, nó biết classpath ở đâu
    // `getResourceAsStream`: mở file classpath như một luồng type

    if (is == null) {
      throw new RuntimeException("Cannot find gs1-prefixes.csv in classpath");
    }

    try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
      // Đây là cú pháp try-with-resource, Java sẽ tự động gọi .close() khi thoát khỏi block dù
      // thành công hay exception

      // try-with-resource là cách Java giải quyết vấn đề resource leak từ Java 7
      reader.readLine();
      String line;

      while ((line = reader.readLine()) != null) {
        String[] parts = line.split(",");
        int start = Integer.parseInt(parts[0]);
        int end = Integer.parseInt(parts[1]);
        String country = parts[2];

        for (int i = start; i <= end; ++i) {
          prefixMap.put(String.valueOf(i), country);
        }

        line = reader.readLine();
      }
      reader.close();
    } catch (IOException e) {
      System.out.println("Error: " + e.getMessage());
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
