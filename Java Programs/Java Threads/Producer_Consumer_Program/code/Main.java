/**
 * 
 */
package code;

/**
 * @author Willoughby Peppler-Mann
 *
 * 
Description: 
This program simulates the Sandwich Making Chefs Problem. 
This system consists of three chef threads and one agent thread.
A chef is responsible for making a peanut butter and jam sandwich
using the ingredients bread, jam, and peanut butter. Each of the
three chefs have an infinite supply of one of the ingredients and
lack the other two. For example, chefA will have bread, chefB will
have jam, and chefC will have peanut butter. The agent has an
infinite supply of all three ingredients. The agent randomly picks
two ingredients and puts them onto a table. The chef with the remaining
ingredient then grabs the ingredients off the table and makes a sandwich.
Once the chef makes a sandwich the agent then puts out two more ingredients.
The process continues forever. In this program though only 20 sandwiches are made.
 */
public class Main {

	
	public static void main(String[] args) {
		  
		
		Tracker tracker = new Tracker(); // create a tracker
		Table t = new Table(); // create a table 
		
		// put the tracker and table in each thread
		Thread chefA = new Chef(new Bread(), t, "chefA", tracker); // chefA has bread
		Thread chefB = new Chef(new Jam(), t, "chefB", tracker); // chefB has jam
		Thread chefC = new Chef(new PeanutButter(), t, "chefC", tracker); // chefC has peanut butter
		Agent agent = new Agent(t, tracker);
		
		
		System.out.println("Starting Program"); 
		
		System.out.println("Starting chefA");
		chefA.start();
		System.out.println("Starting chefB");
		chefB.start();
		System.out.println("Starting chefC");
		chefC.start();
		System.out.println("Starting agent");
		agent.start();
		
		while(agent.isAlive()) {} // while the agent is running let the threads continue
		
		
		
		System.out.println("Simulation Done!");
		
		
		
	}

}
