/**
 * HEADER
 * 
 * Date: Feb. 29, 2022
 */
package code;


/***
 * 
 * @author Willoughby Peppler-Mann
 *
 *
 *	This class is used to track sandwiches being made. 
 *	The tracker will receive a signal from a chef telling the
 *  tracker that the chef has made a sandwich. The tracker
 *  then signals the agent to put new ingredients on the table.
 */
public class Tracker{
	private boolean sandwichMade = false;
	private int numberOfSandwhiches = 0; ;
	
	/***
	 * This method is used to tell the agent if new ingredients are needed 
	 */
	public synchronized void needIngredients() { 
		
		// can only make 20 sandwiches so don't need ingredients if 20 have been made
		while (!sandwichMade && numberOfSandwhiches < 20) {
			try {
				wait();
			} catch (InterruptedException e) {
				return;
			}
		}
		
		
		sandwichMade = false;
		
		notifyAll();
	}
	
	/***
	 * This method called by a chef to signal they made a sandwich
	 * @param chefName the chef that made the sandwich
	 * @param chefIngredient the ingredient the chef has an infinite supply of
	 */
	public synchronized void chefMadeSandwich(String chefName, String chefIngredient) {
		while(sandwichMade) {
			try {
				wait();
			} catch (InterruptedException e) {
				return;
			}
		}
		sandwichMade = true;
		numberOfSandwhiches++;
		
		System.out.println("C - "+chefName + " made sandwhich #"+numberOfSandwhiches + " chef already had "+ chefIngredient);
		
		notifyAll();
	}
	
}
