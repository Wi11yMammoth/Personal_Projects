# Dice Game

## Author
Willoughby Peppler-Mann

## Description
This is a simple dice game program written in java. You start with $100
and the computer starts with $100. The objective of the game is to win all the computers money. First you bet an amount of money. The program then rolls an amount of dice for you and the same amount of dice for the computer. If the sum of the dice you roll is greater than the sum of the dice the computer rolls then you will the amount of money you bet from the computer. If the computer rolled a higher sum then you give the computer the amount you bet. This cycle continues until either you win all the computer's money or the computer wins all your money.

## Installation
Download the Dice Game Folder.

## Compile The Program (Assuming you hava java installed)
1. In the terminal navigate to the Dice Game folder.
2. Use the command javac DiceGame.java
3. You will now have a DiceGame java executable file.

## Run The program
1. Use the command
"java DiceGame" to run the DiceGame executable.
2. You can add 2 arguments to the program. The first argument is the number of sides the dice should have and the second argument is the number of dice the program should have. If no arguments are provided the program uses the default setting of two six sided dice.

For example, running

java DiceGame 4 3

starts a DiceGame with 3, 4-sided dice.

java DiceGame

starts a dice game with 2, 6-sided dice.
