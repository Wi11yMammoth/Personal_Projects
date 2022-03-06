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

/***
 * This class is used to house the Product and the amount of that
 * product available for sale in the same location
 *
 * @author Willoughby Peppler-Mann
 */
public class ProductAndAmount {
    private Product product;
    private int amount;

    /***
     * Constructor used to create a ProductAndAmount object
     * @param product holds a Product object
     * @param amount holds the amount of the Product available for purchase
     */
    public ProductAndAmount(Product product, int amount){
        this.product = product;
        this.amount = amount;
    }

    /***
     * This method returns the id of the Product object stored
     * in this Object
     * @return product id
     */
    public String getId(){ return this.product.getId(); }

    /***
     * This method returns the Product object
     * @return the Product
     */
    public Product getProduct(){ return this.product; }

    /***
     * This method returns the amount of product
     * @return the amount of the product
     */
    public int getAmount(){ return this.amount; }

    /***
     * This method is used to set the amount to a new amount
     * the old amount will be overwritten only if the input
     * amount is >= 0
     * @param amount the amount of this Product in inventory
     * @return true if amount was successfully overwritten; false otherwise
     */
    public boolean setAmount(int amount) {
        boolean amountSet = false;
        if (amount >= 0){
            this.amount = amount;
            amountSet = true;
        }

        return amountSet;
    }

    /***
     * This method adds numItems to the amount if numItems >= 0
     * @param numItems the amount of Product to add to inventory
     * @return true if numItems was successfully added to amount; false otherwise
     */
    public boolean addProduct(int numItems) {
        boolean addSuccessful = false;

        if (numItems >= 0) {
            this.amount = this.amount + numItems;
            addSuccessful = true;
        }

        return addSuccessful;
    }

    /***
     * This method removes numItems from the amount if numItems >= 0
     * @param numItems amount to remove from amount
     * @return true if numItems where removed from amount; false otherwise
     */
    public boolean removeProduct(int numItems) {
        boolean removeSuccessful = false;

        // cannot have negative stock therefore numItems must be <= amount to remove numItems
        if (numItems <= this.amount && numItems >= 0) {
            this.amount = this.amount - numItems;
            removeSuccessful = true; // items were removed
        }

        return removeSuccessful;

    }

    /***
     * This method prints the Product and amount information on screen to user
     */
    public void printInfo(){
        System.out.print("NAME: "+this.getProduct().getName()+ " ");
        System.out.print("ID: "+ this.getProduct().getId()+" ");
        System.out.print("PRICE: $"+ this.getProduct().getPrice()+ " ");
        System.out.print("AMOUNT: "+this.getAmount()+"\n");
    }


}
