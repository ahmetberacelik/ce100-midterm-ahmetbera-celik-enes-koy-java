/**

 @file FarmermarketAppTest.java
 @brief This file contains the test cases for the FarmermarketApp class.
 @details This file includes test methods to validate the functionality of the FarmermarketApp class. It uses JUnit for unit testing.
 */
package com.bera.farmermarket;

import static org.junit.Assert.*;

import java.io.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
/**

 @class FarmermarketAppTest
 @brief This class represents the test class for the FarmermarketApp class.
 @details The FarmermarketAppTest class provides test methods to verify the behavior of the FarmermarketApp class. It includes test methods for successful execution, object creation, and error handling scenarios.
 @author ugur.coruh
 */
public class FarmermarketAppTest {
  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;
  private final ByteArrayInputStream inContent = new ByteArrayInputStream("4\n".getBytes());
  FarmermarketApp farmermarketapp = new FarmermarketApp();
  /**
   * @brief This method is executed before each test method.
   * @throws Exception
   */
  @Before
  public void setUp() throws Exception {
    System.setOut(new PrintStream(outContent));
    System.setIn(inContent);
  }

  /**
   * @brief This method is executed after each test method.
   * @throws Exception
   */
  @After
  public void tearDown() throws Exception {
    System.setOut(originalOut);
    System.setIn(System.in);
  }
  @Test
  public void testFarmermarketAppMain() throws IOException, InterruptedException, ClassNotFoundException {
    String[] args = null;
    FarmermarketApp.main(args);

    String expectedOutputStartsWith = "+---------------------------+";
    String actualOutput = outContent.toString();

    assertEquals(true, actualOutput.startsWith(expectedOutputStartsWith));
  }
}
