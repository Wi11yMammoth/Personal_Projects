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
import java.util.ArrayList;

/***
 * This class is an auxiliary class used to generate some StoreViews to test the main program
 * @author Willoughby Peppler-Mann
 */
public class StoreViewGenerator {
    /***
     * This class creates a few StoreView objects for the purpose of testing the application
     * @param sm a StoreManager
     * @return an ArrayList of active StoreView objects
     */
    public static ArrayList<StoreView> generateStoreViews(StoreManager sm){
        ArrayList<StoreView> sViews = new ArrayList<StoreView>();

        for(int i =0; i < 3; i++){
            sViews.add(new StoreView(sm, sm.assignNewCartID()));
        }
        return sViews;
    }
}
