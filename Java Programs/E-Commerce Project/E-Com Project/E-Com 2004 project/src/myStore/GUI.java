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
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;

/***
 * This class is used to create a GUI for the Store
 */
public class GUI {
    private final JFrame frame;
    private static StoreManager sm;
    private String cartID;

    /***
     * Constructor used to create a GUI object
     * @param sm the StoreManager for the store
     * @param cartID the cartID for the user's cart
     */
    public GUI(StoreManager sm, String cartID){
        this.frame = new JFrame();
        this.sm = sm;
        this.cartID = cartID;
    }


    /***
     * This method is used to create an ItemPanel object
     * An ItemPanel is a JPanel that contains an interface
     * using two buttons that allows the user to add and remove
     * the designated product to and from their cart
     * @param cartID the ID for the user's cart
     * @param paa a ProductAndAmount object
     * @param imageFile the image of the product
     * @return a JPanel
     */
    private static JPanel getItemPanel(String cartID, ProductAndAmount paa, String imageFile){
        JPanel itemPanel = new JPanel(new GridBagLayout());
        JPanel infoPanel = new JPanel(new GridLayout(3,1));
        JLabel itemName = new JLabel("Product: "+ paa.getProduct().getName());
        JLabel itemID = new JLabel("ID: "+ paa.getId());
        JLabel itemCost = new JLabel("Cost: "+String.valueOf(paa.getProduct().getPrice()));
        JLabel imageLabel = new JLabel();
        ImageIcon image = new javax.swing.ImageIcon(GUI.class.getResource(imageFile));
        JLabel stocklb = new JLabel("Stock: "+ String.valueOf(sm.getStock(paa.getId())));

        JLabel cartStock = new JLabel("Cart: 0"); // nothing in cart when initialized
        JPanel cartAndInv = new JPanel(new BorderLayout());

        imageLabel.setIcon(image);
        infoPanel.add(itemName);
        infoPanel.add(itemID);
        infoPanel.add(itemCost);
        itemPanel.add(infoPanel);
        itemPanel.add(getAddButton(stocklb,cartStock,cartID, paa.getId()));
        itemPanel.add(getSubButton(stocklb,cartStock,cartID,paa.getId()));
        cartAndInv.add(stocklb, BorderLayout.PAGE_START);
        cartAndInv.add(cartStock, BorderLayout.PAGE_END);
        itemPanel.add(cartAndInv);
        itemPanel.add(imageLabel);


        return itemPanel;
    }

    /***
     * This method is used to create a button that allows the
     * user to remove a product from their cart 1 product
     * gets removed each time the button is clicked.
     * If there is no product in their cart nothing happens when
     * the button is pressed.
     * @param inv a JLabel that shows the products inventory amount
     * @param cart a JLabel that shows the amount of the product in the user's cart
     * @param cartID the user's cart ID
     * @param productID the product ID to link the button to a specific product
     * @return a JButton
     */
    private static JButton getSubButton(JLabel inv, JLabel cart, String cartID, String productID){
        JButton button = new JButton("-");
        // add the action listener
        button.addActionListener(new ActionListener() {

            // this method will be called when we click the button
            @Override
            public void actionPerformed(ActionEvent ae) {
                sm.removeFromCart(cartID, productID,1);
                inv.setText("Stock: "+String.valueOf(sm.getStock(productID)));
                if (sm.getAmountOfCartItem(cartID,productID) < 0){
                    cart.setText("Cart: 0");
                }else{
                    cart.setText("Cart: " + String.valueOf(sm.getAmountOfCartItem(cartID, productID)));
                }

            }
        });

        return button;
    }

    /***
     * This method is used to create a button that allows the
     * user to add a product from the inventory to their cart
     * 1 product gets added each time the button is pressed.
     * If there is no product left in the inventory then the
     * button does nothing.
     * @param inv a JLabel that shows the products inventory amount
     * @param cart a JLabel that shows the amount of the product in the user's cart
     * @param cartID the user's cart ID
     * @param productID the product ID to link the button to a specific product
     * @return a JButton
     */
    private static JButton getAddButton(JLabel inv, JLabel cart, String cartID, String productID){
        JButton button = new JButton("+");
        // add the action listener
        button.addActionListener(new ActionListener() {

            // this method will be called when we click the button
            @Override
            public void actionPerformed(ActionEvent ae) {
                sm.addToCart(cartID, productID,1);
                inv.setText("Stock: "+String.valueOf(sm.getStock(productID)));
                if (sm.getAmountOfCartItem(cartID,productID) < 0){
                    cart.setText("Cart: 0");
                }else{
                    cart.setText("Cart: " + String.valueOf(sm.getAmountOfCartItem(cartID, productID)));
                }

            }
        });

        return button;
    }

    /***
     * This method is used to create a JPanel that contains
     * all the information about all the products in the
     * inventory and all the buttons that allow the user
     * to add products from the inventory to the user's cart
     * and remove products from the user's cart
     * @param cartID the user's cart ID
     * @return a JPanel
     */
    private static JPanel getStoreBody(String cartID){
        JPanel storeBody = new JPanel(new GridLayout(5,1));

        HashMap<String, ProductAndAmount> inv = sm.getInventory();
        String[] images = new String[]{"arizona.jpg", "sprite.jpg", "coke.jpg","rum.jpg","brisk.jpg"};
        int i = 0;
        for (String id : inv.keySet()){
            storeBody.add(getItemPanel(cartID, inv.get(id), images[i]));
            i++;
        }

        return storeBody;
    }

    /***
     * This method is used to display the user's cart contents
     *
     * @param frame the frame for the program
     * @param cartID the user's cart ID
     */
    private static void displayCart(JFrame frame, String cartID){
        HashMap<String, ProductAndAmount> cart = sm.getAShoppingCart(cartID);
        String message = "";
        double cartTotal = 0.0;
        for (String id : cart.keySet()){
            message = message + String.format("Amount: %d | Product: %s | ID: %s | Cost $%.2f\n",
                    cart.get(id).getAmount(), cart.get(id).getProduct().getId(), cart.get(id).getId(),
                    cart.get(id).getAmount() * cart.get(id).getProduct().getPrice());
            cartTotal = cartTotal + cart.get(id).getAmount() * cart.get(id).getProduct().getPrice();


        }

        message = message + "Total : $" + String.format("%.2f",cartTotal);
        JOptionPane.showMessageDialog(frame, message, "My Cart", JOptionPane.PLAIN_MESSAGE);

    }

    /***
     * This method is used to create a button that allows the user
     * to view their cart's contents when pressed
     * @param frame the programs JFrame
     * @param cartID the user's cart ID
     * @return JButton
     */
    private static JButton getViewCartButton(JFrame frame,String cartID){
        JButton button = new JButton("VIEW CART");
        // add the action listener
        button.addActionListener(new ActionListener() {

            // this method will be called when we click the button
            @Override
            public void actionPerformed(ActionEvent ae) {
                displayCart(frame, cartID);

            }
        });

        return button;
    }

    /***
     * This method is used to create a button that allows the user to
     * exit and close the program
     * @param frame the JFrame for the program
     * @return a JButton
     */
    private static JButton getQuitButton(JFrame frame){
        JButton button = new JButton("Quit");
        // add the action listener
        button.addActionListener(new ActionListener() {

            // this method will be called when we click the button
            @Override
            public void actionPerformed(ActionEvent ae) {
                int n = JOptionPane.showConfirmDialog(frame, "Are you sure you want to exit?", "Confirm Exit",JOptionPane.YES_NO_OPTION);
                if (n<1) {
                    frame.setVisible(false);
                    frame.dispose();
                }


            }
        });

        return button;
    }

    /***
     * This method is used to create a button that
     * checks the user out. Allows the user to purchase
     * all the items in their cart.
     * @param frame the programs JFrame
     * @param cartID the cart ID
     * @return a JButton
     */
    private static JButton getCheckoutButton(JFrame frame,String cartID){
        JButton button = new JButton("Checkout");
        // add the action listener
        button.addActionListener(new ActionListener() {

            // this method will be called when we click the button
            @Override
            public void actionPerformed(ActionEvent ae) {
                int n = JOptionPane.showConfirmDialog(frame, "Do you want to check out?", "Confirm Checkout",JOptionPane.YES_NO_OPTION);
                // if n == 1 then NO if n == 0 then YES
                if (n<1) {
                    HashMap<String, ProductAndAmount> cart = sm.getAShoppingCart(cartID);
                    String message = "";
                    double cartTotal = 0.0;
                    for (String id : cart.keySet()){
                        message = message + String.format("Amount: %d | Product: %s | ID: %s | Cost $%.2f\n",
                                cart.get(id).getAmount(), cart.get(id).getProduct().getId(), cart.get(id).getId(),
                                cart.get(id).getAmount() * cart.get(id).getProduct().getPrice());
                        cartTotal = cartTotal + cart.get(id).getAmount() * cart.get(id).getProduct().getPrice();


                    }

                    message = message + "Total : $" + String.format("%.2f",cartTotal) + "\n";
                    message = message + " THANK YOU FOR YOUR PURCHASE! :)";
                    JOptionPane.showMessageDialog(frame, message, "ORDER SUMMARY", JOptionPane.PLAIN_MESSAGE);
                    sm.emptyCart(cartID);

                    frame.setVisible(false);
                    frame.dispose();
                }
            }
        });

        return button;
    }


    /***
     * This method is used to create a JPanel that contains
     * the view cart button, checkout button, and quit button
     * @param frame the programs JFrame
     * @param cartID the user's cart ID
     * @return a JPanel
     */
    private static JPanel getCheckoutPanel(JFrame frame, String cartID){
        JPanel checkoutPanel = new JPanel(new FlowLayout());

        checkoutPanel.add(getViewCartButton(frame, cartID));
        checkoutPanel.add(getCheckoutButton(frame, cartID));
        checkoutPanel.add(getQuitButton(frame));
        return checkoutPanel;
    }

    /***
     * This method is used to create a JPanel that
     * contains information about the store
     * @return a JPanel
     */
    private static JPanel getStoreDescriptionPanel(){
        JPanel storeDescPanel = new JPanel(new BorderLayout());

        // logo
        JLabel imageLabel = new JLabel();
        ImageIcon image = new javax.swing.ImageIcon(GUI.class.getResource("logo.jpg"));
        imageLabel.setIcon(image);

        String storeDescription = "Hello! Welcome To The SLURP STORE!\n" +
                "We sell a variety of drinks here!\n"+
                "We are the leading experts in quenching the peoples thirst\n\n"+
                "Unofficial winner of the SYSC 2004 least creative store award\n\n"+
                "Slogan: 'we might go to jail for tax evasion'\n\n"+
                "Our customers often ask why does the store look so bad?\n"+
                "The answer:\n" +
                "Because we did the bare minimum on the user interface";


        JTextPane textPane = new JTextPane();
        textPane.setText(storeDescription);
        textPane.setPreferredSize(new Dimension(300,1000));
        storeDescPanel.add(imageLabel, BorderLayout.NORTH);
        storeDescPanel.add(textPane, BorderLayout.CENTER);


        return storeDescPanel;

    }

    /***
     * This method is used to create and display the GUI on screen for the
     * user to interact with
     */
    public void displayGUI(){
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel headerPanel = new JPanel();

        // HEADER
        JLabel header = new JLabel("SLURP STORE (ID:"+this.cartID+")");
        headerPanel.add(header);
        mainPanel.add(headerPanel,BorderLayout.PAGE_START);
        //STORE
        JPanel storeBody = new JPanel(new BorderLayout());
        JLabel storeLb = new JLabel("STORE PRODUCTS");
        JScrollPane scrollPane = new JScrollPane(getStoreBody(this.cartID));
        scrollPane.setPreferredSize(new Dimension(500,200));
        storeBody.add(storeLb, BorderLayout.NORTH);
        storeBody.add(scrollPane, BorderLayout.CENTER);
        storeBody.setBackground(new Color(255,255,0));
        mainPanel.add(storeBody, BorderLayout.WEST);
        // CHECKOUT
        mainPanel.add(getCheckoutPanel(this.frame, this.cartID), BorderLayout.CENTER);

        mainPanel.add(getStoreDescriptionPanel(), BorderLayout.EAST);


        mainPanel.setPreferredSize(new Dimension(1000,500));

        // LOGO / ADMIN


        // pack
        frame.add(mainPanel);
        frame.pack();

        // add the window listener
        // we no longer want the frame to close immediately when we press "x"
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                if (JOptionPane.showConfirmDialog(frame, "Are you sure you want to quit?")
                        == JOptionPane.OK_OPTION) {
                    // close it down!
                    frame.setVisible(false);
                    frame.dispose();
                }
            }
        });

        // the frame is not visible until we set it to be so
        frame.setVisible(true);
    }
}
