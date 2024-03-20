/**
 @file Farmermarket.java
 @brief This file serves as a demonstration file for the Farmermarket class.
 @details This file contains the implementation of the Farmermarket class, which provides various mathematical operations.
 */
/**
 @package com.bera.farmermarket
 @brief The com.bera.farmermarket package contains all the classes and files related to the Farmermarket App.
 */
package com.bera.farmermarket;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 @class Farmermarket
 @brief This class represents a Farmermarket that performs mathematical operations.
 @details The Farmermarket class provides methods to perform mathematical operations such as addition, subtraction, multiplication, and division. It also supports logging functionality using the logger object.
 @author bera
 */
public class Farmermarket {
  private Scanner scanner; /**<Scanner for user input in the Library System. */
  private PrintStream out; /**<PrintStream for output in the Library System. */
  private String filename = "Users.bin";
  String[] vendors = { "Ahmet", "Mehmet", "Veli", "Ayse" };
  String[][] products = {
          {"Ahmet", "Banana", "Apple", "Grape", "Spinach"},
          {"Mehmet", "Raspberry", "Beet", "Turnip", "Peas"},
          {"Veli", "Cucumber", "Tomato", "Orange", "Radish"},
          {"Ayse", "Pear", "Nectarine", "Bean", "Hazelnut"}
  };
  ProductSeason[] productSeasons = {
          new ProductSeason(10, "Banana", "Summer"),
          new ProductSeason(10, "Apple", "Fall"),
          new ProductSeason(15, "Grape", "Fall"),
          new ProductSeason(30, "Spinach", "Spring"),
          new ProductSeason(15, "Raspberry", "Summer"),
          new ProductSeason(20, "Beet", "Fall"),
          new ProductSeason(25, "Turnip", "Winter"),
          new ProductSeason(30, "Peas", "Spring"),
          new ProductSeason(25, "Cucumber", "Summer"),
          new ProductSeason(30, "Tomato", "Summer"),
          new ProductSeason(30, "Orange", "Winter"),
          new ProductSeason(15, "Radish", "Spring"),
          new ProductSeason(35, "Pear", "Fall"),
          new ProductSeason(35, "Nectarine", "Summer"),
          new ProductSeason(40, "Bean", "Summer"),
          new ProductSeason(40, "Hazelnut", "Fall")
  };
  private int budget;
  boolean guestMode = false;
  /**
   * @brief Constructor for the Farmermarket class.
   *
   * This constructor initializes a Farmermarket object with the specified input stream for user input
   * and output stream for printing messages.
   *
   * @param in The input stream for user input.
   * @param out The output stream for printing messages.
   */
  public Farmermarket(InputStream in, PrintStream out) {
    this.scanner = new Scanner(in);
    this.out = out;
  }
  /**
   * @brief Waits for the user to press Enter.
   *
   * This method is used to pause execution and wait for the user to press Enter.
   * @throws IOException If an I/O error occurs.
   */
  private void take_enter_input() throws IOException {
    System.in.read();
  }
  /**
   * @brief Clears the console screen.
   *
   * This method detects the operating system to determine the appropriate command for clearing the console screen.
   * On Windows, it uses the "cmd /c cls" command, while on other operating systems, it uses ANSI escape codes to clear the screen.
   *
   * @throws InterruptedException If the thread is interrupted while waiting for the process to complete.
   * @throws IOException If an I/O error occurs during the execution of the process.
   */
  public void clearScreen() throws InterruptedException, IOException {
    String operatingSystem = System.getProperty("os.name");
    if (operatingSystem.contains("Windows")) {
      new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    } else {
      out.print("\033[H\033[2J");
      out.flush();
    }
  }
  private int tryParseInt(String value) {
    try {
      return Integer.parseInt(value);
    } catch (NumberFormatException e) {
      return -1;
    }
  }

  public boolean mainMenu() throws IOException, InterruptedException, ClassNotFoundException {
    if (!userAuthentication()) {
      return false;
    }
    while (true) {
      clearScreen();
      out.println("+-------------------------------------+");
      out.println("|            MAIN MENU                |");
      out.println("+-------------------------------------+");
      out.println("| 1. Listing of Local Vendors         |");
      out.println("|    and Products                     |");
      out.println("| 2. Seasonal Produce Guide           |");
      out.println("| 3. Price Comparison                 |");
      out.println("| 4. Market Informations              |");
      out.println("| 5. Exit                             |");
      out.println("+-------------------------------------+");
      out.print("Please select an option: ");
      if (!scanner.hasNextInt()) {
        out.print("Invalid choice. Please enter a number.\n");
        take_enter_input();
        scanner.next();
        continue;
      }
      int choice = scanner.nextInt();
      scanner.nextLine();
      switch (choice) {
        case 1:
          listingOfInfos();
          break;
        case 2:
          seasonalProduceGuide();
          break;
        case 3:
          purchasingTransactionsAndPriceComparison();
          break;
        case 4:
          out.println("he");
          break;
        case 5:
          out.println("Exiting program...Press enter!");
          take_enter_input();
          return true;
        default:
          out.println("Invalid option, please try again.");
          take_enter_input();
          break;
      }
    }
  }

  public void saveUser(User user) throws IOException {
    try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(filename, true))) {
      dos.writeUTF(user.getUsername());
      dos.writeUTF(user.getPassword());
    }
  }

  public boolean authenticateUser(String username, String password) throws IOException {
    boolean isAuthenticated = false;
    try (DataInputStream dis = new DataInputStream(new FileInputStream(filename))) {
      while (dis.available() > 0) {
        String storedUsername = dis.readUTF();
        String storedPassword = dis.readUTF();
        if (username.equals(storedUsername) && password.equals(storedPassword)) {
          isAuthenticated = true;
          break;
        }
      }
    }
    return isAuthenticated;
  }

  public boolean userAuthentication() throws IOException, InterruptedException, ClassNotFoundException {
    clearScreen();
    User user1 = new User("Ahmet Bera Celik", "qwerty");
    saveUser(user1);
    User user2 = new User("Enes Koy", "123456");
    saveUser(user2);
    User user3 = new User("Ugur Coruh", "asdasd");
    saveUser(user3);
    String temp_username;
    String temp_password;
    int rightToTry = 3;

    while (true) {
      clearScreen();
      out.println("+---------------------------+");
      out.println("|  LOGIN AND REGISTER MENU  |");
      out.println("+---------------------------+");
      out.println("| 1. Login                  |");
      out.println("| 2. Register               |");
      out.println("| 3. Guest Mode             |");
      out.println("| 4. Exit Program           |");
      out.println("+---------------------------+");
      out.print("Please select an option: ");
      if (!scanner.hasNextInt()) {
        out.print("Invalid choice. Please enter a number.\n");
        take_enter_input();
        scanner.next();
        continue;
      }
      int choice = scanner.nextInt();
      scanner.nextLine();
      switch (choice) {
        case 1:
          out.print("Please enter your username: ");
          temp_username = scanner.nextLine();
          out.print("Please enter your password: ");
          temp_password = scanner.nextLine();
          out.print("Please enter your budget: ");
          budget = tryParseInt(scanner.nextLine());
          if (budget == -1) {
            out.println("Budget must be a numerical value!");
            continue;
          }
          if (authenticateUser(temp_username, temp_password)) {
            out.println("Welcome " + temp_username);
            take_enter_input();
            return true;
          } else {
            rightToTry--;
            out.println("Incorrect username or password. You have " + rightToTry + " more tries.");
            if (rightToTry == 0) {
              out.println("You have run out of login attempts. See you...");
              take_enter_input();
              return false;
            }
          }
          take_enter_input();
          break;
        case 2:
          out.println("Please enter your username: ");
          temp_username = scanner.nextLine();

          out.println("Please enter your password: ");
          temp_password = scanner.nextLine();

          User newUser = new User(temp_username, temp_password);
          saveUser(newUser);
          clearScreen();
          out.println("User registered successfully.\nWelcome " + temp_username);
          out.println("Your budget is 100 tl.");
          budget = 100;
          take_enter_input();
          return true;
        case 3:
          guestMode = true;
          return true;
        case 4:
          out.println("Exiting program...Press enter!");
          take_enter_input();
          return false;
        default:
          out.println("Invalid option, please try again.");
          take_enter_input();
          break;
      }
    }
  }
  public boolean swap(String[] arr, int index1, int index2) {
    String temp = arr[index1];
    arr[index1] = arr[index2];
    arr[index2] = temp;
    return true;
  }
  public int partition(String[] arr, int low, int high) {
    String pivot = arr[low + (int) (Math.random() * (high - low + 1))];
    int i = low - 1;
    int j = high + 1;
    while (true) {
      do {
        i++;
      } while (arr[i].compareTo(pivot) < 0);
      do {
        j--;
      } while (arr[j].compareTo(pivot) > 0);
      if (i >= j) {
        return j;
      }
      swap(arr, i, j);
    }
  }
  public boolean quickSort(String[] arr, int low, int high) {
    if (low < high) {
      int pi = partition(arr, low, high);
      quickSort(arr, low, pi);
      quickSort(arr, pi + 1, high);
    }
    return true;
  }
  public int binarySearch(String[] arr, int l, int r, String x) {
    if (r >= l) {
      int mid = l + (r - l) / 2;
      int res = arr[mid].compareTo(x);
      if (res == 0) {
        return mid;
      }
      if (res > 0) {
        return binarySearch(arr, l, mid - 1, x);
      }
      return binarySearch(arr, mid + 1, r, x);
    }
    return -1;
  }
  public boolean searchAndPrintResult(String[] arr, int size, String x) {
    int result = binarySearch(arr, 0, size - 1, x);
    if (result == -1) {
      return false;
    } else {
      return true;
    }
  }
  public boolean browseVendor() throws IOException, InterruptedException {
    out.print("Please enter vendor name: ");
    String vendorQuery = scanner.nextLine();

    quickSort(vendors, 0, vendors.length - 1);

    clearScreen();
    if (searchAndPrintResult(vendors, vendors.length, vendorQuery)) {
      out.println("Vendor found: " + vendorQuery);
      take_enter_input();
      return true;
    } else {
      out.println("Vendor not found.");
      take_enter_input();
      return false;
    }
  }

  public boolean searchProduct() throws IOException, InterruptedException {
    out.print("Please enter product name: ");
    String productQuery = scanner.nextLine();

    for (String[] vendorProducts : products) {
      quickSort(vendorProducts, 1, vendorProducts.length - 1);
      clearScreen();
      int result = binarySearch(vendorProducts, 1, vendorProducts.length - 1, productQuery);
      if (result != -1) {
        out.println("Product " + productQuery + " found at vendor " + vendorProducts[0]);
        take_enter_input();
        return true;
      }
    }
    out.println("Product not found.");
    take_enter_input();
    return false;
  }
  public boolean listingOfInfos() throws IOException, InterruptedException {
    while (true) {
      clearScreen();
      out.println("+----------------------------+");
      out.println("|   LISTING OF INFORMATIONS  |");
      out.println("+----------------------------+");
      out.println("| 1. Browse Vendors          |");
      out.println("| 2. Search Product          |");
      out.println("| 3. Exit                    |");
      out.println("+----------------------------+");
      out.print("Please select an option: ");
      if (!scanner.hasNextInt()) {
        out.println("Invalid input, please enter a number.");
        take_enter_input();
        scanner.next();
        continue;
      }
      int choice = scanner.nextInt();
      scanner.nextLine();

      switch (choice) {
        case 1:
          if (browseVendor()) {
            take_enter_input();
          }
          break;
        case 2:
          if (searchProduct()) {
            take_enter_input();
          }
          break;
        case 3:
          return true;
        default:
          out.println("Invalid option, please try again.");
          take_enter_input();
          break;
      }
    }
  }
  public void saveProductSeason(ProductSeason[] productSeasons, String filename) throws IOException {
    try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(filename))) {
      dos.writeInt(productSeasons.length);
      for (ProductSeason productSeason : productSeasons) {
        dos.writeInt(productSeason.getPrice());
        dos.writeUTF(productSeason.getName());
        dos.writeUTF(productSeason.getSeason());
      }
    }
  }

  public int loadProductSeasonsAndPrint(String filename, String selectedSeason) throws IOException {
    int found = 0;
    try (DataInputStream dis = new DataInputStream(new FileInputStream(filename))) {
      int numProducts = dis.readInt();

      MinHeap heap = new MinHeap();
      for (int i = 0; i < numProducts; i++) {
        int price = dis.readInt();
        String name = dis.readUTF();
        String season = dis.readUTF();

        if (season.equalsIgnoreCase(selectedSeason)) {
          heap.insert(new ProductSeason(price, name, season));
          found++;
        }
      }

      while (!heap.isEmpty()) {
        ProductSeason ps = heap.remove();
        out.printf("|- Price: %d, Name: %s\n", ps.getPrice(), ps.getName());
      }
      out.println("+------------------------------------+");
      take_enter_input();
    }
    return found;
  }
  public boolean seasonalProduceGuide() throws IOException, InterruptedException {
    clearScreen();
    String filename = "ProductSeasons.bin";
    saveProductSeason(productSeasons, filename);
    while (true) {
      clearScreen();
      out.println("+------------------------------------------+");
      out.println("|      SEASONAL PRODUCE GUIDE              |");
      out.println("+------------------------------------------+");
      out.println("| Select a season to see available produce:|");
      out.println("+------------------------------------------+");
      out.println("| 1. Spring                                |");
      out.println("| 2. Summer                                |");
      out.println("| 3. Fall                                  |");
      out.println("| 4. Winter                                |");
      out.println("| 5. Return to Main Menu                   |");
      out.println("+------------------------------------------+");
      out.print("Please select an option: ");

      int choice = scanner.nextInt();
      scanner.nextLine();

      if (choice == 5) {
        break;
      }

      String selectedSeason = null;
      switch (choice) {
        case 1: selectedSeason = "Spring"; break;
        case 2: selectedSeason = "Summer"; break;
        case 3: selectedSeason = "Fall"; break;
        case 4: selectedSeason = "Winter"; break;
        default:
          out.println("Invalid option, please try again.");
          continue;
      }
      clearScreen();
      out.println("+------------------------------------+");
      out.printf("|Available produce for %s season:%n", selectedSeason);
      out.println("+------------------------------------+");
      loadProductSeasonsAndPrint(filename, selectedSeason);
    }
    return true;
  }
  private int lcs(String X, String Y) {
    int m = X.length();
    int n = Y.length();
    int[][] L = new int[m+1][n+1];

    for (int i=0; i<=m; i++) {
      for (int j=0; j<=n; j++) {
        if (i == 0 || j == 0)
          L[i][j] = 0;
        else if (X.charAt(i-1) == Y.charAt(j-1))
          L[i][j] = L[i-1][j-1] + 1;
        else
          L[i][j] = Math.max(L[i-1][j], L[i][j-1]);
      }
    }
    return L[m][n];
  }

  private boolean compareAndPrintLCS(String season1, String season2, String name1, String name2, int price) {
    int lcsLength = lcs(name1, name2);

    if (lcsLength > 0 ) {
      out.printf("|- Name 1: %s, Name 2: %s, Price: %d\n", name1, name2, price);
      return true;
    }
    return false;
  }
  public int knapsack(int W, int[] wt, int[] val, int n, int[] selectedItems) {
    int i, w;
    int K[][] = new int[n+1][W+1];

    for (i = 0; i <= n; i++) {
      for (w = 0; w <= W; w++) {
        if (i==0 || w==0)
          K[i][w] = 0;
        else if (wt[i-1] <= w)
          K[i][w] = Math.max(val[i-1] + K[i-1][w-wt[i-1]], K[i-1][w]);
        else
          K[i][w] = K[i-1][w];
      }
    }

    int res = K[n][W];
    w = W;
    for (i = n; i > 0 && res > 0; i--) {
      if (res == K[i-1][w])
        continue;
      else {
        selectedItems[i-1] = 1;
        res = res - val[i-1];
        w = w - wt[i-1];
      }
    }
    return res;
  }
  public boolean suggestPurchases(int budget) {
    int[] wt = new int[productSeasons.length];
    int[] val = new int[productSeasons.length];
    for (int i = 0; i < productSeasons.length; i++) {
      wt[i] = productSeasons[i].getPrice();
      val[i] = 1;
    }

    int[] selectedItems = new int[productSeasons.length];
    int maxValue = knapsack(budget, wt, val, productSeasons.length, selectedItems);
    out.println("+----------------------------------------------------+");
    out.println("| Your budget: " + budget + "|");
    out.println("+----------------------------------------------------+");
    out.println("| Suggested purchases to maximize value within budget: |");
    out.println("+----------------------------------------------------+");
    boolean anySelected = false;
    for (int i = 0; i < productSeasons.length; i++) {
      if (selectedItems[i] == 1) {
        out.println("|- " + productSeasons[i].getName() + " for " + productSeasons[i].getPrice());
        anySelected = true;
      }
    }
    if (!anySelected) {
      out.println("No products suggested. Increase your budget or check back later for different options.");
    }
    out.println("+----------------------------------------------------+");
    return anySelected;
  }
  public boolean compareProducts() throws IOException {
    out.print("Enter a season: ");
    String selectedSeason = scanner.nextLine();

    boolean validSeason = false;
    boolean found = false;
    for (ProductSeason productSeason : productSeasons) {
      if (productSeason.getSeason().equalsIgnoreCase(selectedSeason)) {
        validSeason = true;
      }
    }
    if (!validSeason) {
      out.println("Invalid season. Please enter a valid season.");
      take_enter_input();
      return false;
    }
    out.println("+-----------------------------------------------------+");
    out.println("|Products at the same price as " + selectedSeason + " season products:");
    out.println("+-----------------------------------------------------+");
    for (int i = 0; i < productSeasons.length; i++) {
      if (productSeasons[i].getSeason().equalsIgnoreCase(selectedSeason)) {
        for (int j = i + 1; j < productSeasons.length; j++) {
          if (productSeasons[i].getPrice() == productSeasons[j].getPrice()) {
            compareAndPrintLCS(selectedSeason, productSeasons[j].getSeason(), productSeasons[i].getName(), productSeasons[j].getName(), productSeasons[i].getPrice());
            found = true;
          }
        }
      }
    }
    out.println("+-----------------------------------------------------+");
    return true;
  }
  public boolean buyProducts() {
    out.print("Please enter the product name you wish to buy: ");
    String productQuery = scanner.nextLine();

    boolean productFound = false;
    int productPrice = 0;
    String vendorName = null;

    for (ProductSeason productSeason : productSeasons) {
      if (productSeason.getName().equalsIgnoreCase(productQuery)) {
        productFound = true;
        productPrice = productSeason.getPrice();
        break;
      }
    }
    if (!productFound) {
      out.println("Product not found. Please ensure the product name is spelled correctly.");
      return false;
    } else if (budget < productPrice) {
      out.printf("Insufficient budget to buy %s. Your current budget is %d.\n", productQuery, budget);
      return false;
    } else {
      budget -= productPrice;
      out.printf("You have successfully purchased %s for %d. Remaining budget: %d.\n", productQuery, productPrice, budget);
      return true;
    }
  }
  public boolean purchasingTransactionsAndPriceComparison() throws IOException, InterruptedException {
    while (true) {
      clearScreen();
      out.println("+----------------------------------+");
      out.println("|  PURCHASING AND PRICE COMPARISON |");
      out.println("+----------------------------------+");
      out.println("| 1. Shopping Suggestion           |");
      out.println("| 2. Compare Products              |");
      out.println("| 3. Buy Products                  |");
      out.println("| 4. Exit                          |");
      out.println("+----------------------------------+");
      out.print("Please select an option: ");
      if (!scanner.hasNextInt()) {
        out.print("Invalid choice. Please enter a number.\n");
        take_enter_input();
        scanner.next();
        continue;
      }
      int choice = scanner.nextInt();
      scanner.nextLine();

      switch (choice) {
        case 1:
          clearScreen();
          if (guestMode) {
            out.println("You cannot take suggestions in guest mode.");
          } else {
            suggestPurchases(budget);
          }
          take_enter_input();;
          break;
        case 2:
          clearScreen();
          compareProducts();
          take_enter_input();
          break;
        case 3:
          clearScreen();
          if (guestMode) {
            out.println("You cannot buy products in guest mode.");
          } else {
            buyProducts();
          }
          take_enter_input();
          break;
        case 4:
          return true;
        default:
          out.println("Invalid option, please try again.");
          take_enter_input();
          break;
      }
    }
  }

  
}
