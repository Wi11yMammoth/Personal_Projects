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
 * This class is used to test the StoreManager class
 * @author Willoughby Peppler-Mann
 */
public class StoreManagerTest {
    private static StoreManager sm;
    private static String cartID;

    /***
     * Initialize a StoreManager object and a cart to test the functionality
     */
    @BeforeAll
    public static void init(){
        sm = new StoreManager();
        cartID = sm.assignNewCartID();
    }

    /***
     * Test to ensure inventory was generated correctly
     */
    @Test
    public void InventoryInitializationTest(){
        assertEquals(10,sm.getStock("000")); // inventory should initially be 10 for all items
        assertEquals(10,sm.getStock("001"));
        assertEquals(-1,sm.getStock("ABC")); // if invalid product id then should return -1
    }


    /***
     * shopping cart tests check that inventory gets updated accordingly with the shopping cart
     */
    @Test
    public void shoppingCartTests(){
        assertEquals(true, sm.addToCart(cartID, "000", 10));
        assertEquals(false, sm.addToCart(cartID, "000", -1));
        assertEquals(false, sm.addToCart(cartID, "000", 0));
        assertEquals(false, sm.addToCart(cartID, "ABC", 9));
        assertEquals(false, sm.addToCart("bad ID", "000", 5));
        assertEquals(0,sm.getStock("000"));

        assertEquals(true, sm.removeFromCart(cartID, "000", 1));
        assertEquals(false, sm.removeFromCart(cartID, "000", -4));
        assertEquals(false, sm.removeFromCart(cartID, "ABC", 6));
        assertEquals(false, sm.removeFromCart("bad id", "000", 5));
        assertEquals(1, sm.getStock("000"));

        sm.deleteCart(cartID);
        assertEquals(10, sm.getStock("000"));


    }



}
