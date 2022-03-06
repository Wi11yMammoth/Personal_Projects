/**
 *  HEADER
 * 
 * Date: Feb. 29, 2022
 */
package code;

/**
 * @author Willoughby Peppler-Mann (101147027)
 *
 */

/*
 * The chef thread is responsible for taking ingredients off the table and using them to make
 * a sandwich 
 */
class Chef extends Thread { 
	Object ingredient; // the ingredient the chef has
	Table table; // the shared table
	String chefName; 
	Tracker tracker; // chef signals when they make a sandwich
	Ingredients ingredientsFromTable = null; // holds the ingredients the chef gets from the table
	
	/*
	 * Constructor used to create a Chef Thread 
	 * 
	 * @param ingredient holds the ingredient the chef has (i.e. Bread, PeanutButter, or Jam)
	 * @param table holds the shared Table
 	 * @param chefName holds the name of the chef (i.e. chefA, chefB, or chefC)
 	 * @param tracker helps track the number of sandwiches made the chef signals the tracker when they have made a sandwich
	 */
	public Chef(Object ingredient, Table table, String chefName, Tracker tracker) { 
		this.ingredient = ingredient;
		this.table = table;
		this.chefName = chefName;
		this.tracker = tracker;
	}
	
	/**
	 * This method is called when the chef has the ingredients to make a sandwich
	 * */
	private void makeSandwich() {
		tracker.chefMadeSandwich(chefName, ingredient.toString()); 
		ingredientsFromTable = null; // chef used ingredients
	}

	/*
	 * This method executes the task the chef performs 
	 */
	public void run() {
		
		while(true) {
			
			// get ingredients from the table if available
			ingredientsFromTable = table.getAllBut(this.ingredient); 
			
			// if chef successfully got the ingredients then make a sandwich
			if(ingredientsFromTable != null) {
				makeSandwich();
			} 
			
		}
		
	}
}
