package com.bera.farmermarket;

import java.io.IOException;

public class FarmermarketApp {

  public static void main(String[] args) throws ClassNotFoundException, IOException, InterruptedException {
    Farmermarket farmermarket = new Farmermarket(System.in, System.out);
    farmermarket.mainMenu();
  }
}
