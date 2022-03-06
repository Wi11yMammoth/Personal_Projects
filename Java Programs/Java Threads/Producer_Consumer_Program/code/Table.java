/**
 * HEADER
 * 
 * Date: Feb. 29, 2022
 */

package code;

/*
 * @author Willoughby Peppler-Mann (101147027)
 * 
 *  
 *  The Table is used to hold ingredients 
 *  The agent puts ingredients on the table and a chef removes those ingredients
 */
public class Table {
	
	private Ingredients contents = null; // table contents
	
	private boolean empty = true; // empty?
	    
	
	/**
	 * This method is used to put ingredients onto the table
	 * 
	 * @param b holds Bread or null
	 * @param j holds Jam or null
	 * @param pb holds PeanutButter or null
	 * */
	public synchronized void put(Bread b, Jam j, PeanutButter pb) {
		while (!empty) {
			try {
				wait();
			} catch (InterruptedException e) {
				return;
			}
		}
		
		this.contents = new Ingredients(b, pb, j); 
		empty = false;
		notifyAll();
	}
    
	/***
	 * This method is used to remove and get the current ingredeints on the table
	 * @param chefIngredient the ingredient the chef looking at the table has
	 * @return returns the ingredients if the chef doesn't have any of them
	 * 		   returns null otherwise
	 */
	public synchronized Ingredients getAllBut(Object chefIngredient) {
		while (empty) {
			try {
				wait();
			} catch (InterruptedException e) {
				return null;
			}
		}
       
		// check if table has the ingredient the chef has
		if (this.contents.missing(chefIngredient)) {
			// the chef does not have the ingredients and takes them
			Ingredients ingredients = this.contents;
			this.contents = null;
       	   	empty = true;
       	   	notifyAll();
       	   	return ingredients;
		}
		
		// the chef did have one of the ingredients (it's not their turn)
		notifyAll();
		return null; 
	}
    


}
