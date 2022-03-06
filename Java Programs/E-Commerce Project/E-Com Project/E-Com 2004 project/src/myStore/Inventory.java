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
 * This class is used to create an Inventory object
 *
 * @author Willoughby Peppler-Mann
 */
public class Inventory {
    // ATTRIBUTES //
    private HashMap<String, ProductAndAmount> inventory;

    // CONSTRUCTORS //

    /***
     * Default Constructor used to create an Inventory object
     * Uses the InventoryGenerator class to generate the inventory contents
     */
    public Inventory(){
        inventory = InventoryGenerator.generateInventory();
    }

    // METHODS //

    /***
     * This method returns the amount of a given product in the inventory
     * given the product id
     * @param id the product ID
     * @return the amount of the product in the inventory; -1 if the product
     * does not exist in the inventory
     */
    public int getAmount(String id){
        if (this.inventory.containsKey(id)){
            return this.inventory.get(id).getAmount();
        }else{
            return -1;
        }
    }


    /***
     * This method is used to set the amount of a product in the inventory
     * given the product id and the amount to set the inventory amount to
     * @param id the product ID
     * @param amount the amount to set the inventory amount to
     * @return true if the inventory amount was successfully set to the input amount;
     * false otherwise
     */
    public boolean setAmount(String id, int amount){
        boolean amountSet = false;
        if(this.inventory.containsKey(id)){
            amountSet = this.inventory.get(id).setAmount(amount);
        }
        return amountSet;
    }

    /***
     * This method is used to add an amount of product to the inventory amount
     * given the product id and the amount you want to add
     * @param id the product id
     * @param amount the amount to add to the inventory
     * @return true if the amount was added successfully to the inventory amount; false
     * otherwise
     */
    public boolean addAmount(String id, int amount){
        boolean addSuccess = false;
        if(this.inventory.containsKey(id)){
            addSuccess = this.inventory.get(id).addProduct(amount);
        }
        return addSuccess;
    }

    /***
     * This method is used to remove an amount of product from the inventory amount
     * given the product id and the amount to remove
     * @param id the product ID
     * @param amount the amount to remove from inventory
     * @return true if the amount was successfully removed; false otherwise
     */
    public boolean removeAmount(String id, int amount){
        boolean removeSuccess = false;
        if(this.inventory.containsKey(id)){
            removeSuccess = this.inventory.get(id).removeProduct(amount);
        }
        return removeSuccess;
    }

    /***
     * This method is used to add a new Product and the amount of the product
     * to the inventory (product cannot already exist in the inventory)
     * @param product the Product to add to the inventory
     * @param amount the amount of the product
     * @return true if the Product was added successfully; false otherwise
     */
    public boolean addNewProduct(Product product, int amount){
        boolean addSuccess = false;
        if(!this.inventory.containsKey(product.getId())){
            this.inventory.put(product.getId(),new ProductAndAmount(product,amount));
            addSuccess = true;
        }
        return addSuccess;
    }

    /***
     * This method is used to get a Product from the inventory given the Product ID
     * @param id the product ID
     * @return the Product from the inventory; null if product does not exist in inventory
     */
    public Product getProduct(String id){
        if (this.inventory.containsKey(id)) {
            return this.inventory.get(id).getProduct();
        }else{
            return null;
        }
    }

    /***
     * This method is used to print the current inventory contents on screen
     */
    public void printInventory(){
        for (String id : this.inventory.keySet()){
            this.inventory.get(id).printInfo();
        }
    }

    /***
     * This method is used to print the inventory data of an Product given the
     * product ID prints 'Product not found' if the product isn't in the inventory
     * @param id the product ID
     */
    public void printItem(String id){

        if (this.inventory.containsKey(id)) {
            this.inventory.get(id).printInfo();
        }else{
            System.out.println("Product not found");
        }
    }

    /***
     * This method is used to get a copy the HashMap of the current inventory
     * @return HashMap<String, ProductAndAmount>
     */
    public HashMap<String,ProductAndAmount> getInventory(){
        HashMap<String, ProductAndAmount> copy = new HashMap<String, ProductAndAmount>(this.inventory);
        return copy;
    }
}
