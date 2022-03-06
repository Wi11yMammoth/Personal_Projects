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
 * This class is used to test the ProductAndAmount class
 * @author Willoughby Peppler-Mann
 */
public class ProductAndAmountTest {
    private static ProductAndAmount p;

    /***
     * Initialize a ProductAndAmount object with
     * name = 'Arizona'
     * id = '000'
     * price = 1.99
     * amount = 10
     */
    @BeforeAll
    public static void init(){
        p = new ProductAndAmount(new Product("Arizona", "000", 1.99), 10);
    }

    /***
     * Test to make sure ID is correct
     */
    @Test
    public void idTest(){
        assertEquals("000",p.getProduct().getId());
    }

    /***
     * Test to make sure name is correct
     */
    @Test
    public void nameTest(){
        assertEquals("Arizona",p.getProduct().getName());
    }

    /***
     * Test to make sure price is correct
     */
    @Test
    public void priceTest(){
        assertEquals(1.99, p.getProduct().getPrice());
    }

    /***
     * Test that the amount is initialized correctly and gets correctly updated
     * using different methods
     */
    @Test
    public void amountTests(){
        // TEST INITIALIZATION
        assertEquals(10, p.getAmount());
        // TEST REMOVE
        assertEquals(true, p.removeProduct(10));
        assertEquals(0, p.getAmount());
        // TEST ADD
        assertEquals(true, p.addProduct(8));
        assertEquals(8,p.getAmount());
        // TEST SET
        assertEquals(true,p.setAmount(69));
        assertEquals(69,p.getAmount());
        // TEST INVALID PARAMETER VALUES
        assertEquals(false,p.removeProduct(100));
        assertEquals(false,p.removeProduct(-6));
        assertEquals(false,p.addProduct(-8));
        assertEquals(false,p.setAmount(-1));
        assertEquals(69,p.getAmount());
    }




}
