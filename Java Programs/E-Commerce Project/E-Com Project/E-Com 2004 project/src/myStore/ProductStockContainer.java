package myStore;

public interface ProductStockContainer {
    /***
     * A method for getting the quantity of a given product given the product ID
     * @param id a product ID for a product to get the quantity of
     * @return int - the quantity
     */
    int getProductQuantity(String id);


    boolean addProductQuantity(Product product, int amount);
    boolean removeProductQuantity(Product product, int amount);
    int getNumOfProducts();
}
