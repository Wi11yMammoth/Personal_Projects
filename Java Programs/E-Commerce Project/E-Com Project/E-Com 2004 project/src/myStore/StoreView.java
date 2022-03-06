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
import java.util.ArrayList;
import java.util.Scanner;

/***
 * This class is used to create a StoreView object
 *
 * @author Willoughby Peppler-Mann
 */
public class StoreView {
    // ATTRIBUTES //
    private StoreManager storeManager;
    private String cartID;

    // CONSTRUCTORS //

    /***
     * This constructor is used to create a StoreView object
     * @param sm a StoreManager object
     * @param id an ID for the shopping cart assigned to this StoreView
     */
    public StoreView(StoreManager sm, String id){
        cartID = id;
        storeManager = sm;
    }

    // METHODS //

    // MAIN //

    /***
     * This is the main program for the E-Commerce project
     *
     * This program operates a store and manages a variety of users purchasing
     * merchandise form the store
     * @param args
     */
    public static void main(String[] args){
        StoreManager sm = new StoreManager();
        ArrayList<StoreView> storeViews = new ArrayList<StoreView>(); // ArrayList of active StoreViews
        StoreView sView; // holds current StoreView
        storeViews = StoreViewGenerator.generateStoreViews(sm);// generate some StoreViews
        int storeViewIndex; // holds the index of the current StoreView in use from the storeViews list


        storeViewIndex = 0;
        sView = storeViews.get(storeViewIndex);

        String command;
        command = modeSelect();
        if (command.equals("y")) {
            // GRAPHICAL USER INTERFACE
            GUI storeGUI = new GUI(sm, sView.getCartID());
            storeGUI.displayGUI();
        }else {
            // COMMAND LINE INTERFACE
            boolean shopping = true; // users are shopping

            while (shopping) {
                command = sView.mainMenu();
                switch (command) {
                    case "help":
                        sView.printCommandList();
                        break;
                    case "add":
                        sView.addToCart();
                        break;
                    case "view i":
                        sView.printInventory();
                        break;
                    case "view c":
                        sView.printCart();
                        break;
                    case "remove":
                        sView.removeFromCart();
                        break;
                    case "pay":
                        sView.checkout();
                        break;
                    case "search":
                        sView.search();
                        break;
                    case "move":
                        sView = moveStoreView(storeViews, storeViewIndex);
                        break;
                    case "new":
                        storeViews.add(new StoreView(sm, sm.assignNewCartID()));
                        System.out.println("New StoreView added to list of active StoreViews");
                        break;
                    case "sViews":
                        printStoreViews(storeViews);
                        break;
                    case "quit":
                        if (sView.quit()) {
                            storeViews.remove(storeViewIndex);
                            if (storeViews.size() > 0) {
                                // move to another active StoreView
                                sView = storeViews.get(0);
                                storeViewIndex = 0;
                            } else {
                                System.out.println("No more StoreViews program done! :) ");
                                shopping = false;
                            }
                        }
                        break;
                    case "exit":
                        shopping = sView.confirmExit();
                        break;

                    default:
                        System.out.print("'" + command + "' is not a command\n");

                }

                // pauses the program  to let user view results of previous command
                // before asking for next command
                if (shopping) {
                    sView.waitForUser();
                }

            }
        }

    }

    // STATIC METHODS //

    /***
     * This method prints the list of active StoreViews on screen
     * @param sViews an ArrayList of active StoreViews
     */
    private static void printStoreViews(ArrayList<StoreView> sViews){
        System.out.println("printing active StoreView list...");
        for(StoreView s : sViews){
            System.out.println("StoreView Cart ID - "+ s.getCartID());
        }
        System.out.println("Done printing active StoreViews");
    }

    /***
     * This method allows the user to choose an active StoreView from a list of
     * StoreViews and this method returns an active StoreView based on the users input
     * @param sViews an ArrayList of active StoreViews
     * @param currentIndex the index of the current StoreView in use in sViews
     * @return a StoreView object from the sViews list
     */
    private static StoreView moveStoreView(ArrayList<StoreView> sViews, int currentIndex){
        Scanner in = new Scanner(System.in);
        int index;
        for (int i=0; i<sViews.size(); i++){
            System.out.println("StoreView '"+i+"' Cart ID:"+sViews.get(i).cartID);
        }

        System.out.println("Enter number for which store view to move to");
        System.out.print(">>>");

        try {
            index = in.nextInt();

            if (index < 0 || index >= sViews.size()){
                System.out.println("Invalid StoreView value");
                System.out.println("Staying on Current StoreView");
                index = currentIndex;
            }

        }catch(Exception e){
            index = currentIndex;
            System.out.println("Invalid Input - non integer value");
            System.out.println("Staying on Current StoreView");
        }

        return sViews.get(index);

    }

    // METHODS //

    /***
     * This method returns the cart ID of this StoreView
     * @return the cart ID assigned to this StoreView
     */
    public String getCartID(){ return this.cartID; }

    /***
     * This method allows a user to search for a Product in the inventory and prints the
     * inventory results on screen
     */
    private void search(){
        Scanner in = new Scanner(System.in);
        String id;
        System.out.println("search routine....");
        System.out.println("Enter product id to search");
        System.out.print(">>>");
        id = in.nextLine();
        System.out.println("Inventory results");
        this.storeManager.printItemInventory(id);
    }

    /***
     * This method confirms with the user that they want to quit
     * @return true if user does want to quit; false otherwise
     */
    private boolean quit(){
        Scanner in = new Scanner(System.in);
        String command;
        boolean quit = false;

        System.out.println("Are you sure you want to quit? (y/n)");
        System.out.print(">>>");

        command = in.nextLine();

        switch(command){
            case "y","Y":
                this.storeManager.deleteCart(this.cartID);
                quit = true;
                break;
            default:
                break;
        }

        return quit;
    }

    /***
     * This method confirms with the user that they want to exit
     * @return true if the user doesn't want to exit; false otherwise
     */
    private boolean confirmExit(){
        String command;
        Scanner in = new Scanner(System.in);
        boolean dontExit = true;
        System.out.println("Are you sure you want to exit? (y/n)");
        command = in.nextLine();

        switch(command){
            case "Y","y":
                dontExit = false;
                break;
            default:
                break;
        }

        return dontExit;
    }

    /***
     * This method is the main menu routine for the StoreView
     * @return a String for the command entered by the user
     */
    private String mainMenu(){
        String command;
        Scanner input = new Scanner(System.in);
        this.printMainMenu();
        System.out.print("Enter command >>> ");
        command = input.nextLine();
        return command;

    }

    /***
     * This method is the main menu routine for the StoreView
     * @return a String for the command entered by the user
     */
    private static String modeSelect(){
        String command;
        Scanner input = new Scanner(System.in);
        System.out.println("Enter GUI y/n?");
        System.out.println("If you don't enter 'y' then the command line");
        System.out.println("interface will be used instead");
        System.out.print("Enter command >>> ");
        command = input.nextLine();
        return command;

    }

    /***
     * This method prints the main menu on screen for the user
     */
    private void printMainMenu(){
        System.out.print("---------------\n");
        System.out.print("THE SLURP STORE\n");
        System.out.print("---------------\n");
        System.out.print("CART ID - "+this.cartID+ "\n");
        System.out.print("type 'help' for command list\n");

    }

    /***
     * This method pauses the program and waits for the user to hit enter to continue
     */
    private void waitForUser(){
        String trash;
        Scanner in = new Scanner(System.in);

        System.out.println("Press ENTER to continue...");
        trash = in.nextLine();
    }

    /***
     * This method prints the list of valid commands on screen for the user
     */
    private void printCommandList(){
        System.out.println("command - 'view i' to view inventory");
        System.out.println("command - 'view c' to view cart");
        System.out.println("command - 'add' add item(s) to cart");
        System.out.println("command - 'remove' remove item(s) from cart");
        System.out.println("command - 'pay' process transaction");
        System.out.println("command - 'search' search for an item in the inventory");
        System.out.println("command - 'move' move to another active StoreView");
        System.out.println("command - 'sViews' prints the list of active StoreViews");
        System.out.println("command - 'quit' close the current StoreView moves to another active StoreView");
        System.out.println("command - 'new' creates a new StoreView and adds it to the list of active StoreViews");
        System.out.println("command - 'exit' end the program");

    }

    /***
     * This method is the routine that allows the user to add a Product to their cart
     */
    private void addToCart(){
        String id;
        Scanner input = new Scanner(System.in);
        int amount;
        System.out.println("AddToCart routine...");
        System.out.println("Enter product id for the product you want to add to cart");
        System.out.println("(Enter 'c' to cancel)");
        System.out.print(">>>");

        id = input.nextLine();
        if(!id.equals("c")){
            amount = this.storeManager.getStock(id);
            if(amount<0){
                System.out.println("Product with id "+ id + " does not exist in inventory");
            }else if(amount == 0){
                System.out.println("Product is out of stock");
            }else{
                System.out.println("Printing product inventory information...");
                this.storeManager.printItemInventory(id);

                while(true) {
                    try {
                        System.out.println("We have " + amount + " available how many would you like? (enter 0 for none)");
                        System.out.print(">>>");
                        amount = input.nextInt();
                        break;
                    }catch(Exception e){
                        System.out.println("Invalid Input - please enter a valid amount");
                    }
                }
                if(amount > 0 && storeManager.addToCart(this.cartID, id, amount)){
                    System.out.println("Successfully added " + amount+ " of this product to cart");
                }else{
                    System.out.println("Failed to add "+amount+ " of this product to cart");
                }
            }
        }

    }

    /***
     * This is the routine that allows the user to remove a Product from their cart
     */
    private void removeFromCart(){
        Scanner input = new Scanner(System.in);
        String id;
        int amount;

        System.out.println("removeFromCart routine...");
        System.out.println("Enter product id for the product you want to remove");
        System.out.println("(Enter 'c' to cancel)");
        System.out.print(">>>");

        id = input.nextLine();

        if(!id.equals("c")){
            System.out.println("Printing the product's contents in the cart");
            this.storeManager.printCartItem(this.cartID, id);
            amount = this.storeManager.getAmountOfCartItem(this.cartID,id);
            if(amount>0){
                while(true) {
                    try {
                        System.out.println("You have " + amount + " of this product in your cart");
                        System.out.println("How much do you want to remove? (enter 0 for none)");
                        System.out.print(">>>");
                        amount = input.nextInt();
                        break;
                    }catch(Exception e){
                        System.out.println("Invalid input - please enter a valid amount");
                    }
                }

                if(this.storeManager.removeFromCart(this.cartID, id, amount)){
                    System.out.println("Successfully removed "+ amount+ " of this product from cart");
                }else{
                    System.out.println("Failed to remove "+ amount+ " of this product from cart");
                }
            }
        }


    }

    /***
     * This routine outputs the stores inventory on screen for the user to see
     */
    private void printInventory(){
        System.out.println("printInventory routine....");
        this.storeManager.printInventory();
        System.out.println("End of inventory");
    }

    /***
     * This routine outputs the user's cart contents on screen for them to see
     */
    private void printCart(){
        System.out.println("printCart routine...");
        this.storeManager.printCartContents(this.cartID);
        System.out.println("End of cart contents");

    }

    /***
     * This routine allows a user to check out and purchase all the items in their cart
     */
    private void checkout(){ this.storeManager.processTransaction(this.cartID); }


}
