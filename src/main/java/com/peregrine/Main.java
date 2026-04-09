package com.peregrine;

import com.peregrine.facade.BarcodeScanner;
import com.peregrine.processor.BarcodeProcessor;
import com.peregrine.service.GS1PrefixService;
import com.peregrine.validator.BarcodeValidator;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    BarcodeValidator validator = new BarcodeValidator();
    GS1PrefixService service = new GS1PrefixService();

    BarcodeProcessor processor = new BarcodeProcessor(validator, service);

    BarcodeScanner scannerFacade = new BarcodeScanner(processor);

    try (Scanner inputReader = new Scanner(System.in)) {
      String result = scannerFacade.scan(inputReader.nextLine());
      System.out.println(result);
    } catch (Exception e) {
      System.out.println("An error occurred: " + e.getMessage());
    }
  }
}
