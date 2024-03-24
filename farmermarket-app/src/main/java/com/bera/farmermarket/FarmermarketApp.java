/**
 * @file FarmermarketApp.java
 *
 * @brief This file contains the implementation of the FarmermarketApp class.
 */
package com.bera.farmermarket;
import java.io.IOException;
/**
 * The entry point class for the Farmermarket application.
 */
public class FarmermarketApp {
  /**
   * The main method of the application.
   *
   * @param args Command-line arguments (not used).
   * @throws ClassNotFoundException If a class is not found during deserialization.
   * @throws IOException If an I/O error occurs.
   * @throws InterruptedException If a thread is interrupted while sleeping.
   */
  public static void main(String[] args) throws ClassNotFoundException, IOException, InterruptedException {
    Farmermarket farmermarket = new Farmermarket(System.in, System.out);
    boolean authenticationResult = farmermarket.userAuthentication();
    farmermarket.mainMenu(authenticationResult);
  }
}
