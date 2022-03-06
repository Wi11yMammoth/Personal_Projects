# Producer Consumer Program

## Author
Willoughby Peppler-Mann

## Files
- Agent.java
- Bread.java
- Chef.java
- Ingredients.java
- Jam.java
- Main.java
- PeanutButter.java
- Table.java
- Tracker.java

The files Bread.java, Jam.java, and PeantButter.java are object classes that represent
the ingredients.

The Chef.java holds the Chef Thread class.

The Agent.java holds the Agent Thread class.

The Table.java holds the class that represents a table the agent puts ingredients on and
the chefs take ingredients off of.

The Tracker.java class monitors the chefs making sandwiches and signals the agent when
a chef is done making a sandwich.

The Main.java holds the main program that runs the simulation.

## Description
This program simulates the Sandwich making Chefs problem. The Sandwich making Chefs problem is as follows:

Consider a system with three chef threads and one agent thread. Each chef continuously makes a
sandwich and then eats it. But to make and eat a sandwich, the chef needs three ingredients: bread,
peanut butter, and jam. One of the chef threads has an infinite supply of bread, another has peanut
butter, and the third has jam. The agent has an infinite supply of all three ingredients. The agent
randomly selects two of the ingredients and places them on a table. The chef who has the remaining
ingredient then makes and eats a sandwich, signaling the agent on completion. The agent then puts
out another two of the three ingredients, and the cycle repeats.

This program creates three chef threads and one agent thread. Each Chef is responsible for making a peanut butter and jam sandwich. Each of the three chefs have an infinite supply of one of the ingredients and lack the other two. For example, chefA will have bread, chefB will have jam, and chefC will have peanut butter. The agent has an infinite supply of all three ingredients. The agent randomly picks two ingredients and puts them onto the table. The chef with the remaining ingredient then grabs the ingredients off the table and makes a sandwich. Once the chef makes a sandwich the agent then puts two more ingredients on the table. The process continues forever. This program stops once 20 sandwiches are made.

## Installation
This program was written using the Eclipse IDE. I recommend running the program in the same environment. You can download Eclipse here https://www.eclipse.org/downloads/. Once you have Eclipse running you need to import the Producer_Consumer_Program folder. That's it. If you want to run the program using something other than Eclipse the source code is in the code folder.

## To Run The program
Assuming you are using Eclipse open the main.java folder. Navigate to run->run as-> java application. Once you click run as java application the program's output will be visible in the console window.
