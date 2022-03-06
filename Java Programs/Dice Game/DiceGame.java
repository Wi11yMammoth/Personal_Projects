
// WLLOUGHBY PEPPLER-MANN


import java.util.Scanner;

/*
 This class is used to create a DiceGame object
*/
public class DiceGame {
    private Die die;
    private int numberOfDice;
    private int computerMoney;
    private int userMoney;

    /*
    Default constructor used to create a DiceGame object
    */
    public DiceGame(){
      this(6,2);
    }
    /*
    Constructor used to create a DiceGame object
    numSides - int; holds the number of sides on each die
    numDice - int; holds the number of dice in play
    */
    public DiceGame(int numSides, int numDice){
      this.die = new Die(numSides);
      this.numberOfDice = numDice;
      this.userMoney = 100;
      this.computerMoney = 100;
    }

    /*
    This method is used to play the DiceGame
    The DiceGame is a command line betting game in which
    the user plays against the computer
    */
    public void play(){
      Scanner in = new Scanner(System.in);
      int bet;
      int compDiceTotal;
      int userDiceTotal;
      int dieRoll;
      String trash;
      boolean notDone = true;
      while(notDone){
        System.out.print("Welcome to the Game of Dice!\n");
        System.out.print(String.format("Starting a game with %d %d-sided dice\n",
                          this.numberOfDice, this.die.getNumSides()));
        System.out.print("--------------------\n");
        System.out.print(String.format("You have $%d\n", this.userMoney));
        System.out.print(String.format("The computer has $%d\n", this.computerMoney));
        System.out.print("Place your bet (-1 to quit playing) >>>");
        try{
          bet = in.nextInt();
        }catch(Exception e){
          System.out.print("\nInvalid Input - not a valid integer value");
          bet = 0;
          trash = in.nextLine();
        }

        if (bet > 0 && bet <= this.userMoney){

          // USER ROLL
          userDiceTotal = 0;
          System.out.print(String.format("%d Dice :: You rolled > { ", this.numberOfDice));
          for(int i=0; i < this.numberOfDice; i++){
            dieRoll = die.roll();
            System.out.print(dieRoll + " ");
            userDiceTotal = userDiceTotal + dieRoll;
          }
          System.out.print("}\n");

          // COMPUTER ROLL
          compDiceTotal = 0;
          System.out.print(String.format("%d Dice :: Comp rolled > { ", this.numberOfDice));
          for(int i=0; i < this.numberOfDice; i++){
            dieRoll = die.roll();
            System.out.print(dieRoll + " ");
            compDiceTotal = compDiceTotal + dieRoll;
          }
          System.out.print("}\n");

          if(compDiceTotal>userDiceTotal){
            System.out.print("Computer wins this round!\n");
            this.computerMoney = this.computerMoney + bet;
            this.userMoney = this.userMoney - bet;
          }else if(userDiceTotal>compDiceTotal){
            System.out.print("You win this round!\n");
            this.computerMoney = this.computerMoney - bet;
            this.userMoney = this.userMoney + bet;
          }else{
            System.out.print("It's a tie!\n");
          }
          System.out.print("--------------------\n\n");

          // IS GAME OVER?
          if(this.computerMoney<1){
            System.out.print("GAME OVER - YOU WIN!!!\n");
            notDone = false;
          }else if(this.userMoney<1){
            System.out.print("GAME OVER - YOU ARE OUT OF MONEY :(\n");
            notDone = false;
          }

        }else if(bet > this.userMoney || bet < -1){
          System.out.print("\nYou have to bet a positive value, but less than your remaining money!\n");
          System.out.print("--------------------\n\n");
        }else if(bet == -1){
          notDone = false;
        }else{
          System.out.print("\nNo money bet\n");
          System.out.print("--------------------\n\n");
        }



      }
      System.out.println("Good Bye! :)");
    }
    /**
    This is the Main program for the DiceGame
    */
    public static void main(String[] args) {
        DiceGame game;
        int numDice;
        int numSides;
        if (args.length == 2) { // 2 arguments accepted only
          try{
            numSides = Integer.parseInt(args[0]);
            numDice = Integer.parseInt(args[1]);
          }catch(Exception e){
            System.out.print("INVALID Arguments\n");
            numSides = 0;
            numDice = 0;
          }

          if (numSides>1 && numDice >0){
            game = new DiceGame(numSides,numDice);
            game.play();
          }else{
            System.out.print("Cannot Play Game With The Given Arguments\n");
          }
        }else if (args.length == 0){
          game = new DiceGame();
          game.play();
        } else{
          System.out.print("INVALID Arguments\n");
          System.out.print("Cannot Play Game\n");
        }


    }
}
