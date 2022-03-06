// WILLOUGHBY PEPPLER-MANN

import java.util.Random;
/*
This class is used to create a Die object
*/
public class Die {
    private int numSides;

    /*
    Default constructor used to create a six sided die
    */
    public Die(){
      this(6);
    }
    /*
    Constructor used to create a Die object
    numSides - int; holds the number of sides on the die
    */
    public Die(int numSides){
      this.numSides = numSides;
    }

    /*
    This method is used to get the number of sides on the die
    returns an int
    */
    public int getNumSides(){
      return this.numSides;
    }

    /*
    Method used to roll the Die and return a random face value of the Die
    Returns an int
    */
    public int roll(){
      Random roll = new Random();
      return roll.nextInt(this.numSides) + 1;

    }

}
