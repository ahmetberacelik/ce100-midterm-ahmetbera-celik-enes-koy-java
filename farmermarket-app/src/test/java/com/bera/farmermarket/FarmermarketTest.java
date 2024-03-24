/**

 @file FarmermarketTest.java
 @brief This file contains the test cases for the Farmermarket class.
 @details This file includes test methods to validate the functionality of the Farmermarket class. It uses JUnit for unit testing.
 */
package com.bera.farmermarket;

import java.io.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.junit.Assert;

public class FarmermarketTest {
  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;
  private final InputStream originalIn = System.in;
  private Farmermarket farmermarket;
  /**
   * @brief This method is executed before each test method.
   * @throws Exception
   */
  @Before
  public void setUp() throws Exception {
    System.setOut(new PrintStream(outContent));

  }
  /**
   * @brief This method is executed after each test method.
   * @throws Exception
   */
  @After
  public void restoreStreams() {
    System.setOut(originalOut);
    System.setIn(originalIn);
  }
  @Test
  public void UserAuthenticationLoginTest() throws IOException, InterruptedException, ClassNotFoundException {
    ByteArrayInputStream inContent = new ByteArrayInputStream("1\nEnes Koy\n123456\n100\n\n".getBytes());
    System.setIn(inContent);

    farmermarket = new Farmermarket(System.in, System.out);
    boolean result = farmermarket.userAuthentication();

    Assert.assertTrue(result);

  }
  @Test
  public void UserAuthenticationLoginInvalidTest() throws IOException, InterruptedException, ClassNotFoundException {
    ByteArrayInputStream inContent = new ByteArrayInputStream("1\ninvalid\n123\n100\n\n1\ninvalid\n123\n100\n\n1\ninvalid\n123\n100\n\n".getBytes());
    System.setIn(inContent);

    farmermarket = new Farmermarket(System.in, System.out);
    boolean result = farmermarket.userAuthentication();

    Assert.assertFalse(result);
  }
  @Test
  public void UserAuthenticationLoginInvalidBudgetTest() throws IOException, InterruptedException, ClassNotFoundException {
    ByteArrayInputStream inContent = new ByteArrayInputStream("1\ninvalid\n123\nletter\n\n4\n".getBytes());
    System.setIn(inContent);

    farmermarket = new Farmermarket(System.in, System.out);
    boolean result = farmermarket.userAuthentication();

    Assert.assertFalse(result);
  }
  @Test
  public void UserAuthenticationRegisterTest() throws IOException, InterruptedException, ClassNotFoundException {
    ByteArrayInputStream inContent = new ByteArrayInputStream("2\nNew User\n123456\n".getBytes());
    System.setIn(inContent);

    farmermarket = new Farmermarket(System.in, System.out);
    boolean result = farmermarket.userAuthentication();

    Assert.assertTrue(result);

  }
  @Test
  public void LoginGuestModeTest() throws IOException, InterruptedException, ClassNotFoundException {
    ByteArrayInputStream inContent = new ByteArrayInputStream("3\n".getBytes());
    System.setIn(inContent);

    farmermarket = new Farmermarket(System.in, System.out);
    boolean result = farmermarket.userAuthentication();

    Assert.assertTrue(result);

  }
  @Test
  public void ExitUserAuthenticationTest() throws IOException, InterruptedException, ClassNotFoundException {
    ByteArrayInputStream inContent = new ByteArrayInputStream("4\n".getBytes());
    System.setIn(inContent);

    farmermarket = new Farmermarket(System.in, System.out);
    boolean result = farmermarket.userAuthentication();

    Assert.assertFalse(result);

  }
  @Test
  public void UserAuthenticationInvalidInputTest() throws IOException, InterruptedException, ClassNotFoundException {
    ByteArrayInputStream inContent = new ByteArrayInputStream("invalid\n\n456465465\n\n4\n".getBytes());
    System.setIn(inContent);

    farmermarket = new Farmermarket(System.in, System.out);
    boolean result = farmermarket.userAuthentication();

    Assert.assertFalse(result);
  }
  @Test
  public void testTryParseInt() {
    farmermarket = new Farmermarket(System.in, System.out);
    Assert.assertEquals(123, farmermarket.tryParseInt("123"));
    Assert.assertEquals(-1, farmermarket.tryParseInt("abc"));
  }
  @Test
  public void testSwap() {
    farmermarket = new Farmermarket(System.in, System.out);
    String[] arr = {"one", "two", "three"};
    farmermarket.swap(arr, 0, 2);
    Assert.assertArrayEquals(new String[]{"three", "two", "one"}, arr);
  }
  @Test
  public void testPartition() {
    farmermarket = new Farmermarket(System.in, System.out);
    String[] arr = {"three", "one", "two"};
    int pivotIndex = farmermarket.partition(arr, 0, arr.length - 1);
    for (int i = 0; i <= pivotIndex; i++) {
      Assert.assertTrue(arr[i].compareTo(arr[pivotIndex]) <= 0);
    }
    for (int i = pivotIndex + 1; i < arr.length; i++) {
      Assert.assertTrue(arr[i].compareTo(arr[pivotIndex]) >= 0);
    }
  }
  @Test
  public void testQuickSort() {
    farmermarket = new Farmermarket(System.in, System.out);
    String[] arr = {"c", "b", "a"};
    farmermarket.quickSort(arr, 0, arr.length - 1);
    Assert.assertArrayEquals(new String[]{"a", "b", "c"}, arr);
  }
  @Test
  public void testBinarySearch() {
    farmermarket = new Farmermarket(System.in, System.out);
    String[] arr = {"a", "b", "c", "d", "e"};
    Assert.assertEquals(2, farmermarket.binarySearch(arr, 0, arr.length - 1, "c"));
    Assert.assertEquals(-1, farmermarket.binarySearch(arr, 0, arr.length - 1, "z"));
    Assert.assertEquals(1, farmermarket.binarySearch(arr, 0, arr.length - 1, "b"));
  }
  @Test
  public void testSearchAndPrintResult() {
    farmermarket = new Farmermarket(System.in, System.out);
    String[] arr = {"a", "b", "c", "d", "e"};
    Assert.assertTrue(farmermarket.searchAndPrintResult(arr, arr.length, "c"));
    Assert.assertFalse(farmermarket.searchAndPrintResult(arr, arr.length, "z"));
  }
  @Test
  public void VendorSearchTest() throws IOException, InterruptedException, ClassNotFoundException {
    ByteArrayInputStream inContent = new ByteArrayInputStream("Ahmet\n".getBytes());
    System.setIn(inContent);

    farmermarket = new Farmermarket(System.in, System.out);
    boolean result = farmermarket.browseVendor();

    Assert.assertTrue(result);
  }
  @Test
  public void VendorNotFoundTest() throws IOException, InterruptedException, ClassNotFoundException {
    ByteArrayInputStream inContent = new ByteArrayInputStream("NonExistingVendor\n".getBytes());
    System.setIn(inContent);

    farmermarket = new Farmermarket(System.in, System.out);
    boolean result = farmermarket.browseVendor();

    Assert.assertFalse(result);
  }
  @Test
  public void ProductSearchTest() throws IOException, InterruptedException, ClassNotFoundException {
    ByteArrayInputStream inContent = new ByteArrayInputStream("Banana\n".getBytes());
    System.setIn(inContent);

    farmermarket = new Farmermarket(System.in, System.out);
    boolean result = farmermarket.searchProduct();

    Assert.assertTrue(result);
  }
  @Test
  public void ProductNotFoundTest() throws IOException, InterruptedException, ClassNotFoundException {
    ByteArrayInputStream inContent = new ByteArrayInputStream("NonExistingProduct\n".getBytes());
    System.setIn(inContent);

    farmermarket = new Farmermarket(System.in, System.out);
    boolean result = farmermarket.searchProduct();

    Assert.assertFalse(result);
  }
  @Test
  public void ListingInfosMenuValidOptionTest1() throws IOException, InterruptedException, ClassNotFoundException {
    ByteArrayInputStream inContent = new ByteArrayInputStream("1\nAhmet\n\n3\n".getBytes());
    System.setIn(inContent);

    farmermarket = new Farmermarket(System.in, System.out);
    boolean result = farmermarket.listingOfInfos();

    Assert.assertTrue(result);
  }
  @Test
  public void ListingInfosMenuValidOptionTest2() throws IOException, InterruptedException, ClassNotFoundException {
    ByteArrayInputStream inContent = new ByteArrayInputStream("2\nBanana\n\n3\n".getBytes());
    System.setIn(inContent);

    farmermarket = new Farmermarket(System.in, System.out);
    boolean result = farmermarket.listingOfInfos();

    Assert.assertTrue(result);
  }
  @Test
  public void ListingInfosMenuInvalidOptionTest() throws IOException, InterruptedException, ClassNotFoundException {
    ByteArrayInputStream inContent = new ByteArrayInputStream("invalid\n5455\n3\n".getBytes());
    System.setIn(inContent);

    farmermarket = new Farmermarket(System.in, System.out);
    boolean result = farmermarket.listingOfInfos();

    Assert.assertTrue(result);
  }
  @Test
  public void SeasonalProduceGuideTest() throws IOException, InterruptedException, ClassNotFoundException {
    ByteArrayInputStream inContent = new ByteArrayInputStream("invalid\n\n45454\n\n1\n\n2\n\n3\n\n4\n\n5\n".getBytes());
    System.setIn(inContent);

    farmermarket = new Farmermarket(System.in, System.out);
    boolean result = farmermarket.seasonalProduceGuide();

    Assert.assertTrue(result);
  }
  @Test
  public void testLcs() {
    farmermarket = new Farmermarket(System.in, System.out);
    Assert.assertEquals(3, farmermarket.lcs("ABC", "ABDC"));
    Assert.assertEquals(0, farmermarket.lcs("ABC", "DEF"));
  }
  @Test
  public void testCompareAndPrintLCS() {
    farmermarket = new Farmermarket(System.in, System.out);
    Assert.assertTrue(farmermarket.compareAndPrintLCS("Summer", "Summer", "Watermelon", "Melon", 50));
  }
  @Test
  public void testKnapsack() {
    farmermarket = new Farmermarket(System.in, System.out);
    int[] wt = {10, 20, 30};
    int[] val = {60, 100, 120};
    int[] selectedItems = new int[wt.length];
    int W = 50;
    int expected = 0;
    int value = farmermarket.knapsack(W, wt, val, wt.length, selectedItems);
    Assert.assertEquals(expected, value);
  }
  @Test
  public void SuggestPurchasesTest() {
    farmermarket = new Farmermarket(System.in, System.out);
    int budget = 100;
    boolean result = farmermarket.suggestPurchases(budget);
    Assert.assertTrue(result);

    budget = 0;
    result = farmermarket.suggestPurchases(budget);
    Assert.assertFalse(result);
  }
  @Test
  public void CompareProductsTest() throws IOException, InterruptedException, ClassNotFoundException {
    ByteArrayInputStream inContent = new ByteArrayInputStream("Summer\n".getBytes());
    System.setIn(inContent);

    farmermarket = new Farmermarket(System.in, System.out);
    boolean result = farmermarket.compareProducts();

    Assert.assertTrue(result);
  }
  @Test
  public void CompareProductsTestInvalid() throws IOException, InterruptedException, ClassNotFoundException {
    ByteArrayInputStream inContent = new ByteArrayInputStream("InvalidSeason\n".getBytes());
    System.setIn(inContent);

    farmermarket = new Farmermarket(System.in, System.out);
    boolean result = farmermarket.compareProducts();

    Assert.assertFalse(result);
  }
  @Test
  public void BuyProductsTest() throws IOException, InterruptedException, ClassNotFoundException {
    ByteArrayInputStream inContent = new ByteArrayInputStream("Apple\n".getBytes());
    System.setIn(inContent);
    int budget = 10;
    farmermarket = new Farmermarket(System.in, System.out);
    boolean result = farmermarket.buyProducts(budget);

    Assert.assertTrue(result);
  }
  @Test
  public void BuyProductsTestInvalid() throws IOException, InterruptedException, ClassNotFoundException {
    ByteArrayInputStream inContent = new ByteArrayInputStream("NonExistentProduct\n".getBytes());
    System.setIn(inContent);
    int budget = 10;
    farmermarket = new Farmermarket(System.in, System.out);
    boolean result = farmermarket.buyProducts(budget);

    Assert.assertFalse(result);
  }
  @Test
  public void BuyProductsTestLowBudget() throws IOException, InterruptedException, ClassNotFoundException {
    ByteArrayInputStream inContent = new ByteArrayInputStream("Apple\n".getBytes());
    System.setIn(inContent);
    int budget = 0;
    farmermarket = new Farmermarket(System.in, System.out);
    boolean result = farmermarket.buyProducts(budget);

    Assert.assertFalse(result);
  }
  @Test
  public void PurchasingTransactionsAndPriceComparisonMenuTest() throws IOException, InterruptedException, ClassNotFoundException {
    ByteArrayInputStream inContent = new ByteArrayInputStream("1\n\n2\n\n\n\n4\n".getBytes());
    System.setIn(inContent);
    boolean guestMode = true;
    farmermarket = new Farmermarket(System.in, System.out);
    boolean result = farmermarket.purchasingTransactionsAndPriceComparison(guestMode);

    Assert.assertTrue(result);
  }
  @Test
  public void PurchasingTransactionsAndPriceComparisonShoppingSuggestionGuestModeTest() throws IOException, InterruptedException, ClassNotFoundException {
    ByteArrayInputStream inContent = new ByteArrayInputStream("1\n\n\n4\n".getBytes());
    System.setIn(inContent);
    boolean guestMode = false;
    farmermarket = new Farmermarket(System.in, System.out);
    boolean result = farmermarket.purchasingTransactionsAndPriceComparison(guestMode);

    Assert.assertTrue(result);
  }
  @Test
  public void PurchasingTransactionsAndPriceComparisonBuyProductsTest() throws IOException, InterruptedException, ClassNotFoundException {
    ByteArrayInputStream inContent = new ByteArrayInputStream("3\n\n4\n".getBytes());
    System.setIn(inContent);
    boolean guestMode = true;
    farmermarket = new Farmermarket(System.in, System.out);
    boolean result = farmermarket.purchasingTransactionsAndPriceComparison(guestMode);

    Assert.assertTrue(result);
  }
  @Test
  public void PurchasingTransactionsAndPriceComparisonBuyProductsGuestModeTest() throws IOException, InterruptedException, ClassNotFoundException {
    ByteArrayInputStream inContent = new ByteArrayInputStream("3\n\n\n4\n".getBytes());
    System.setIn(inContent);
    boolean guestMode = false;
    farmermarket = new Farmermarket(System.in, System.out);
    boolean result = farmermarket.purchasingTransactionsAndPriceComparison(guestMode);

    Assert.assertTrue(result);
  }
  @Test
  public void PurchasingTransactionsAndPriceComparisonInvalidOptionTest() throws IOException, InterruptedException, ClassNotFoundException {
    ByteArrayInputStream inContent = new ByteArrayInputStream("invalid\n\n4541515\n\n4\n".getBytes());
    System.setIn(inContent);
    boolean guestMode = true;
    farmermarket = new Farmermarket(System.in, System.out);
    boolean result = farmermarket.purchasingTransactionsAndPriceComparison(guestMode);

    Assert.assertTrue(result);
  }
  @Test
  public void testRecursiveMatrixMultiply() {
    int[][] A = {
            {1, 2},
            {3, 4}
    };
    int[][] B = {
            {2, 0},
            {1, 2}
    };
    int[][] C = new int[2][2];
    farmermarket = new Farmermarket(System.in, System.out);
    farmermarket.recursiveMatrixMultiply(A, B, C, 0, 0, 0, 0, 2);

    Assert.assertArrayEquals(new int[]{4, 4}, C[0]);
    Assert.assertArrayEquals(new int[]{10, 8}, C[1]);
  }
  @Test
  public void testMCM_MemorizedRecursive() {
    int[] dimensions = {1, 2, 3, 4};
    farmermarket = new Farmermarket(System.in, System.out);
    farmermarket.initializeDP(4);
    int result = farmermarket.MCM_MemorizedRecursive(dimensions, 1, dimensions.length - 1);
    Assert.assertEquals(18, result);
  }
  @Test
  public void testMCM_MemorizedRecursive_WithPrecomputedValue() {
    int[] dimensions = {1, 2, 3, 4};
    farmermarket = new Farmermarket(System.in, System.out);
    farmermarket.initializeDP(dimensions.length);
    farmermarket.dp[1][3] = 10;
    int result = farmermarket.MCM_MemorizedRecursive(dimensions, 1, 3);
    Assert.assertEquals(10, result);
  }
  @Test
  public void testMCM_DynamicProgramming() {
    int[] dimensions = {1, 2, 3, 4};
    farmermarket = new Farmermarket(System.in, System.out);
    int result = farmermarket.MCM_DynamicProgramming(dimensions);
    Assert.assertEquals(18, result);
  }
  @Test
  public void MarketInformationsTotalIncomeTest() throws IOException, InterruptedException, ClassNotFoundException {
    ByteArrayInputStream inContent = new ByteArrayInputStream("1\n\n2\n\n3\n".getBytes());
    System.setIn(inContent);
    farmermarket = new Farmermarket(System.in, System.out);
    boolean result = farmermarket.marketInformations();

    Assert.assertTrue(result);
  }
  @Test
  public void MarketInformationsInvalidOptionTest() throws IOException, InterruptedException, ClassNotFoundException {
    ByteArrayInputStream inContent = new ByteArrayInputStream("invalid\n\n525451\n\n3\n".getBytes());
    System.setIn(inContent);
    farmermarket = new Farmermarket(System.in, System.out);
    boolean result = farmermarket.marketInformations();

    Assert.assertTrue(result);
  }
  @Test
  public void MainMenuTest() throws IOException, InterruptedException, ClassNotFoundException {
    ByteArrayInputStream inContent = new ByteArrayInputStream("1\n3\n2\n5\n3\n4\n4\n3\nasddsa\n\n151\n\n5\n\n".getBytes());
    System.setIn(inContent);
    boolean authenticationResult = true;
    farmermarket = new Farmermarket(System.in, System.out);
    boolean result = farmermarket.mainMenu(authenticationResult);
    Assert.assertTrue(result);
  }
  @Test
  public void MainMenuTestReturnFalse() throws IOException, InterruptedException, ClassNotFoundException {
    boolean authenticationResult = false;
    farmermarket = new Farmermarket(System.in, System.out);
    boolean result = farmermarket.mainMenu(authenticationResult);
    Assert.assertFalse(result);
  }
}
