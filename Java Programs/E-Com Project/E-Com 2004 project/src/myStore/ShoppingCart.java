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
 * Date: Of last Edit - March 20, 2021
 *
 * @author Willoughby Peppler-Mann
 */

// IMPORT STATEMENTS //
import java.util.HashMap;

/***
 * This class is used to create a ShoppingCart object
 *
 * @author Willoughby Peppler-Mann
 */
public class ShoppingCart {
    // ATTRIBUTES //
    private HashMap<String, ProductAndAmount> cart;

    // CONSTRUCTORS //

    /***
     * Default Constructor used to create a ShoppingCart object
     */
    public ShoppingCart(){
        this.cart = new HashMap<String, ProductAndAmount>();
    }

    // METHODS //

    /***
     * This method is used to empty the contents of the cart
     */
    public void emptyCart(){ cart.clear(); }

    /***
     * This method is used to add a Product and the amount of the
     * Product to the cart given the Product and the amount of the
     * product to add
     * @param product the product to add to cart
     * @param amount the amount to add
     * @return true if the Product was added successfully; false otherwise
     */
    public boolean addToCart(Product product, int amount){
        boolean addSuccess = false;
        if(amount > 0) {
            if (this.cart.containsKey(product.getId())) {
                addSuccess = this.cart.get(product.getId()).addProduct(amount);
            } else {
                this.cart.put(product.getId(), new ProductAndAmount(product, amount));
                addSuccess = true;
            }
        }

        return addSuccess;
    }

    /***
     * This method is used to remove an amount of product from the cart
     * given the Product and the amount of the product to be removed
     * @param product the product to remove
     * @param amount the amount of the product to remove
     * @return true if the amount was removed successfully; false otherwise
     */
    public boolean removeFromCart(Product product, int amount){
        boolean removeSuccess = false;

        if (this.cart.containsKey(product.getId())){
            removeSuccess = this.cart.get(product.getId()).removeProduct(amount);
            if (this.cart.get(product.getId()).getAmount() == 0){
                this.cart.remove(product.getId());
            }
        }

        return removeSuccess;
    }

    /***
     * This method is used to get the amount of a Product in the cart
     * given the product id
     * @param id the product ID
     * @return the amount of the product in the cart; -1 if the product
     * does not exist in the cart
     */
    public int getAmount(String id){
        int amount = -1;
        if (this.cart.containsKey(id)){
            amount = this.cart.get(id).getAmount();
        }
        return amount;
    }

    /***
     * This method returns the total cost of all the products in the cart
     * @return the total cost of all items in the cart; 0.0 if no items in cart
     */
    public double getCartTotal(){
        double amountToPay = 0.0;

        for (String id : cart.keySet()){
            amountToPay = amountToPay + cart.get(id).getAmount() * cart.get(id).getProduct().getPrice();
        }

        return amountToPay;
    }

    /***
     * This method is used to get the cart HashMap
     * @return the cart
     */
    public HashMap<String, ProductAndAmount> getCart(){ return this.cart; }


}
