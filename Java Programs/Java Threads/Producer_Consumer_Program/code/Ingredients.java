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
 * This class is a container that can hold the three ingredients
 * Bread, PeanutButter, and Jam
 */
public class Ingredients {
	public Bread bread = null;
	public PeanutButter peanutButter = null; 
	private Jam jam = null;
	
	/***
	 * Constructor used to great an ingredients object 
	 * @param bread holds Bread or null
	 * @param peanutButter holds PeanutButter or null
	 * @param jam holds Jam or null
	 */
	Ingredients(Bread bread, PeanutButter peanutButter, Jam jam){
		this.bread = bread;
		this.peanutButter = peanutButter;
		this.jam = jam;
	}
	
	/***
	 * This method returns the contents of jam
	 * @return Jam or null
	 */
	public Jam getJam() { return this.jam;}
	
	/***
	 * This method returns the contents of bread
	 * @return Bread or null
	 */
	public Bread getBread() { return this.bread;}
	
	/***
	 * This method returns the contents of peanutButter
	 * @return PeanutButter or null
	 */
	public PeanutButter getPeanutButter() { return this.peanutButter;}
	
	
	/***
	 * This method is used to see if an ingredient is missing from this container
	 * @param ingredient the ingredient to check
	 * @return true if the ingredient is missing and false if it's not
	 */
	public boolean missing(Object ingredient) {
		
		if (ingredient instanceof Bread) {
			return this.bread == null; 
		}else if (ingredient instanceof Jam) {
			return this.jam == null;
		}else {
			return this.peanutButter == null;
		}
		
		
	} 
	
	
}
