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
 * This class is used to create a Product object
 *
 * @author Willoughby Peppler-Mann
 */
public class Product {
    // ATTRIBUTES
    private String name;
    private String id;
    private double price;

    // CONSTRUCTORS

    /***
     * Constructor used to create a product object
     * @param name holds name of the product
     * @param id holds the product ID
     * @param price holds the price of the product
     */
    public Product(String name, String id, double price){
        this.name = name;
        this.id = id;
        this.price = price;

    }


    // METHODS

    /***
     * This method returns the product name
     * @return name
     */
    public String getName() { return name; }

    /***
     * This method returns the product id
     * @return id
     */
    public String getId() { return id; }

    /***
     * This method returns the price of the product
     * @return price
     */
    public double getPrice() { return price;}

    /***
     * This method is used to set the price attribute to a new value
     * @param price the new price of the product
     */
    public void setPrice(double price) { this.price = price; }



}
