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
 * Date: Of last Edit - April 3, 2021
 *
 * @author Willoughby Peppler-Mann
 */

// IMPORT STATEMENTS //
import java.util.HashMap;
import java.util.Random;

/***
 * This class is used to create a StoreManager object
 * @author Willoughby Peppler-Mann
 */
public class StoreManager {
    // ATTRIBUTES //
    private Inventory inventory; // inventory must be updated and same for all StoreManagers
    private HashMap<String, ShoppingCart> shoppingCarts;

    // CONSTRUCTORS //

    /***
     * default constructor used to create a StoreManager object
     */
    public StoreManager(){
        inventory = new Inventory();
        shoppingCarts = new HashMap<String, ShoppingCart>();
    }

    // METHODS //

    /***
     * This method returns a random unique cart ID not already in use
     * @return a unique sequence of characters as a String
     */
    public String assignNewCartID(){
        String newID = "";
        Random rand = new Random();

        boolean notNewID = true;

        while(notNewID) {
            newID = newID + String.format("%d", rand.nextInt(9));
            newID = newID + String.format("%d", rand.nextInt(9));
            newID = newID + String.format("%d", rand.nextInt(9));
            newID = newID + String.format("%d", rand.nextInt(9));
            newID = newID + String.format("%d", rand.nextInt(9));
            newID = newID + String.format("%d", rand.nextInt(9));
            newID = newID + String.format("%d", rand.nextInt(9));
            notNewID = this.shoppingCarts.containsKey(newID);
        }

        this.shoppingCarts.put(newID, new ShoppingCart());
        return newID;
    }

    /***
     * This method is used to get the amount of a Product in the inventory
     * given the Product ID
     * @param id the product ID
     * @return the amount of the Product in the inventory; -1 if the product
     * does not exist in the inventory
     */
    public int getStock(String id){ return this.inventory.getAmount(id); }

    /***
     * This method is used to add an amount of a Product to cart from the inventory
     * @param cartID the cart ID for the cart to add the Product to
     * @param productID the product ID for the Product to add to cart
     * @param amount the amount of the Product ot add to cart
     * @return true if the Product was successfully added to cart; false otherwise
     */
    public boolean addToCart(String cartID, String productID, int amount){
        boolean addSuccess = false;

        if(this.inventory.removeAmount(productID, amount)) {
            addSuccess = this.shoppingCarts.get(cartID).addToCart(this.inventory.getProduct(productID), amount);
            if(!addSuccess){ // if failed to add to cart then put back in inventory
                this.inventory.addAmount(productID, amount);
            }
        }

        return addSuccess;
    }

    /***
     * This method is used to remove an amount of a Product from a shopping cart
     * given the shopping cart ID, the product ID, and the amount to remove
     * @param cartID the cart ID to remove the Product from
     * @param productID the product ID for the Product to be removed
     * @param amount the amount of the Product to remove from the cart
     * @return true if the amount was successfully removed from the cart; false otherwise
     */
    public boolean removeFromCart(String cartID, String productID, int amount){
        boolean removeSuccess = false;
        Product product = this.inventory.getProduct(productID);
        if (this.shoppingCarts.containsKey(cartID) && (product != null)) {
            if (this.shoppingCarts.get(cartID).removeFromCart(this.inventory.getProduct(productID), amount)) {
                removeSuccess = this.inventory.addAmount(productID, amount);
                if (!removeSuccess) {
                    this.shoppingCarts.get(cartID).addToCart(this.inventory.getProduct(productID), amount);
                }
            }
        }

        return removeSuccess;
    }

    /***
     * This method is used to process a transaction for a cart given the cart ID
     * This method prints the cart contents on screen for the user
     * empties the shopping cart
     * prints the total cost of all the items in the cart
     * and thanks the user for their purchase
     * @param cartID the ID for the cart to be processed
     */
    public void processTransaction(String cartID){
        HashMap<String, ProductAndAmount> cart;
        cart = this.shoppingCarts.get(cartID).getCart();

        System.out.println("Processing Transaction....");
        System.out.println("Printing Cart Contents...");
        for(String id : cart.keySet()){
            System.out.print(">");
            cart.get(id).printInfo();
        }
        System.out.print("\nYour Cart total is "+ this.shoppingCarts.get(cartID).getCartTotal());
        System.out.print("\nTHANK YOU FOR YOUR PURCHASE!\n");

        this.shoppingCarts.get(cartID).emptyCart();
    }



    /***
     * This method is used to print the inventory of a product on screen to the user
     * given a product ID
     * @param id the ID for the product in the inventory
     */
    public void printItemInventory(String id){ this.inventory.printItem(id); }

    /***
     * This method prints the whole inventory on screen to the user
     */
    public void printInventory(){ this.inventory.printInventory(); }

    /***
     * This method prints the product information of a given product in a shopping cart
     * given the cart ID and the product ID
     * @param cartID the cart ID
     * @param productID the product ID
     */
    public void printCartItem(String cartID, String productID){
        if(this.shoppingCarts.get(cartID).getCart().containsKey(productID)){
            this.shoppingCarts.get(cartID).getCart().get(productID).printInfo();
        }else{
            System.out.println("Product not found in cart");
        }
    }

    /***
     * This method prints the contents of a shopping cart on screen to the user
     * given the shopping cart ID
     * @param cartID the shopping cart ID
     */
    public void printCartContents(String cartID){
        HashMap<String, ProductAndAmount> cart;
        cart = this.shoppingCarts.get(cartID).getCart();
        if (cart.keySet().size() > 0) {
            for (String id : cart.keySet()) {
                cart.get(id).printInfo();
            }
        }else{
            System.out.println("Cart is empty");
        }
    }

    /***
     * This method returns the amount of a given Product in the shopping cart
     * given the shopping cart ID and the Product ID
     * @param cartID the cart ID
     * @param productID the product ID
     * @return the amount of the Product in the shopping cart; -1 if the
     * product is not in the shopping cart
     */
    public int getAmountOfCartItem(String cartID, String productID){
        int amount = -1;
        if (this.shoppingCarts.get(cartID).getCart().containsKey(productID)){
            amount = this.shoppingCarts.get(cartID).getCart().get(productID).getAmount();
        }
        return amount;
    }

    /***
     * This method is used to delete a shopping cart given the shopping cart ID
     * items that are in cart are put back in inventory
     * @param cartID the ID of the cart to delete
     */
    public void deleteCart(String cartID){
        HashMap<String, ProductAndAmount> cart;

        cart = this.shoppingCarts.get(cartID).getCart();

        for(String id : cart.keySet()){
            this.inventory.addAmount(cart.get(id).getId(), cart.get(id).getAmount());
        }

        this.shoppingCarts.remove(cartID);

    }

    /***
     * This routine removes all items from a shopping cart given the cart ID
     * Use for when user checks out
     * @param cartID cart ID
     */
    public void emptyCart(String cartID){
        this.shoppingCarts.get(cartID).emptyCart();
    }

    /***
     * Returns a copy of the current inventory HashMap - returns copy so inventory cannot be
     * modified outside of class
     * @return HashMap<String, ProductAndAmount>
     */
    public HashMap<String, ProductAndAmount> getInventory(){
        return this.inventory.getInventory();
    }

    /***
     * Returns a copy of a shopping cart - returns copy so shopping cart cannot be
     * modified outside of class
     * @param cartID
     * @return HashMap<String, ProductAndAmount>
     */
    public HashMap<String, ProductAndAmount> getAShoppingCart(String cartID){
        HashMap<String, ProductAndAmount> copy = new HashMap<String, ProductAndAmount>(this.shoppingCarts.get(cartID).getCart());
        return copy;
    }
}
