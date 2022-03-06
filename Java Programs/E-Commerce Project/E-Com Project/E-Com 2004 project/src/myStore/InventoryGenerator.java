package myStore;
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
 * Date: Of last Edit - March 2, 2021
 *
 * @author Willoughby Peppler-Mann
 */
// IMPORT STATEMENTS //
import java.util.HashMap;

/***
 * This is an auxiliary class used to generate the stores inventory contents for now
 */
public class InventoryGenerator {

    /***
     * This method generates the values for the inventory
     * @return the inventory
     */
    public static HashMap<String, ProductAndAmount> generateInventory(){
        HashMap<String, ProductAndAmount> inventory = new HashMap<String, ProductAndAmount>();
        String[] names = {"Arizona", "Coke", "Ice Tea", "Sprite", "rum"};
        String[] ids = {"000","001","010","011", "100"};
        double[] prices = {0.99, 1.25, 2.75, 1.25, 5.00};

        for (int i=0; i < 5; i++){
            inventory.put(ids[i], new ProductAndAmount(new Product(names[i],ids[i],prices[i]),10));
        }

        return inventory;
    }

}
