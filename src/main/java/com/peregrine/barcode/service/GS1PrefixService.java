package com.peregrine.barcode.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class GS1PrefixService {
  private final Map<String, String> prefixMap;

  public GS1PrefixService() {
    prefixMap = new HashMap<>();
    loadPrefixes();
  }

  private void loadPrefixes() {
    InputStream is = getClass().getClassLoader().getResourceAsStream("gs1-prefixes.csv");

    if (is == null) {
      throw new RuntimeException("Cannot find gs1-prefixes.csv in classpath");
    }

    try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
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
      }
    } catch (IOException e) {
      throw new RuntimeException("Failed to load GS1 prefix data", e);
    }
  }

  public String lookup(String barcode) {
    String undeterminedResult = "Unknown";
    if (barcode == null || barcode.length() < 3) {
      return undeterminedResult;
    }

    String prefix = barcode.substring(0, 3);
    String country = prefixMap.get(prefix);
    return country != null ? country : undeterminedResult;
  }
}
