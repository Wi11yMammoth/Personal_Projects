/**
 * HEADER
 * 
 * Date: Feb. 29, 2022
 */
package code;
import java.util.Random; 

/**
 * @author Willoughby Peppler-Mann
 *
 */

/*
 * The agent thread is responsible for putting two ingredients onto the table for the chefs. 
 *  
 * 
 */
class Agent extends Thread {
	
	Table table = null; 
	Bread b = new Bread(); // the infinite supply of bread
	Jam j = new Jam(); // the infinite supply of jam
	PeanutButter pb = new PeanutButter(); // the infinite suppy of peanut butter
	Tracker tracker; 
	
	/*
	 * Constructor used to create an Agent Thread
	 * 
	 * @param table holds the Table shared by the agent and the chefs
	 * @param tracker the tracker is used to signal the agent to put ingredients onto the table
	 */
	public Agent(Table table, Tracker tracker) {
		this.table = table;
		this.tracker = tracker;
	}
	
	/*
	 * This method executes the task the agent performs  
	 */
	public void run() {

		int i; // holds a random integer from 0-3
		Random rand = new Random(); // used to generate the random integer i
		int numSandwiches = 0; // holds the number of sandwiches made
		
		while(true) {
			i = rand.nextInt(3);
			
			if (i<1) {
				System.out.println("A - Agent put bread and jam on table.");
				table.put(b, j, null); // put bread and jam on table
			}else if (i<2) {
				System.out.println("A - Agent put bread and peanut butter on table.");
				table.put(b,  null,  pb); // put bread and peanut buttter on table
			}else {
				System.out.println("A - Agent put peanut butter and jam on table.");
				table.put(null, j, pb); // put jam and peanut butter on table
			}
			
			
			tracker.needIngredients(); // wait for chef to finish making sandwich before sending out new ingredients
			
			numSandwiches++; // a chef has made a sandwich 
			if (numSandwiches > 19) {return;} // only want 20 sandwiches to be made
			
		}
		
		
	}
}
