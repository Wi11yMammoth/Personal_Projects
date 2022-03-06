package storetest;
/**
 * // HEADER //
 *
 * E-COMMERCE PROJECT
 * COURSE - SYSC 2004
 *
 * STUDENT - WILLOUGHBY PEPPLER-MANN
 *
 * INSTRUCTOR - Cristina Ruiz Martin
 *
 * Date: Of last Edit - March 20, 2021
 *
 * @author Willoughby Peppler-Mann
 */

import myStore.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/***
 * This class is used to test the Inventory class
 * @author Willoughby Peppler-Mann
 */
public class InventoryTest {
    private static Inventory i;

    @BeforeEach
    public void init(){
        i = new Inventory();
    }

    /***
     * Test that Inventory is initialized correctly
     */
    @Test
    public void initializationTest(){
        assertEquals(10,i.getAmount("000"));
        assertEquals(10,i.getAmount("001"));
        assertEquals(10,i.getAmount("010"));
    }

    /***
     * Test that adding and removing inventory works
     */
    @Test
    public void addAndRemoveTest(){
        // ADDING
        assertEquals(true, i.addAmount("000", 10));
        assertEquals(false, i.addAmount("000", -5));
        assertEquals(false, i.addAmount("ABC", 5));
        assertEquals(20, i.getAmount("000"));

        // REMOVING
        assertEquals(true, i.removeAmount("000", 20));
        assertEquals(false, i.removeAmount("000", 5));
        assertEquals(false, i.removeAmount("000", -4));
        assertEquals(0, i.getAmount("000"));
    }


    /***
     * Test that a products inventory can be set to a correct amount
     */
    @Test
    public void setTest(){
        assertEquals(true, i.setAmount("000", 5));
        assertEquals(false, i.setAmount("000", -5));
        assertEquals(5,i.getAmount("000"));
    }

    /***
     * Test that a new product can be added to inventory
     */
    @Test
    public void newProductTest(){
        Product p = new Product("Pepsi", "777", 4.20);
        assertEquals(true, i.addNewProduct(p,5));
        assertEquals(5, i.getAmount("777"));
    }


}
