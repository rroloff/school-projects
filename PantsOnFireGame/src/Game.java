///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            Pants on Fire
// Files:            None
// Semester:         CS 302 Spring 2016
//
// Author:           Rita Roloff
// Email:            rroloff@wisc.edu
// CS Login:         rita
// Lecturer's Name:  Jim Williams
// Lab Section:      312
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
//
//                   CHECK ASSIGNMENT PAGE TO see IF PAIR-PROGRAMMING IS ALLOWED
//                   If pair programming is allowed:
//                   1. Read PAIR-PROGRAMMING policy (in cs302 policy) 
//                   2. choose a partner wisely
//                   3. REGISTER THE TEAM BEFORE YOU WORK TOGETHER 
//                      a. one partner creates the team
//                      b. the other partner must join the team
//                   4. complete this section for each program file.
//
// Pair Partner:     Amrita Geddam
// Email:            geddam@wisc.edu
// CS Login:         geddam
// Lecturer's Name:  Jim Williams
// Lab Section:      311



import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
public class Game 
{	
	// This is an instance variable that controls ControlType
	private int controlType;
	//this creates an instance of hero
	private Hero hero;
	// this creates an array of 8 water objects
	private Water[] water = new Water [8];
	//this creates an arrayList of pants
	private ArrayList<Pant> pants = new ArrayList<Pant>();
	//this creates and arrayList of fireballs
	private ArrayList<Fireball> fireballs = new ArrayList<Fireball>();
	//this creates an ArrayList of fires
	private ArrayList<Fire> fires = new ArrayList<Fire>();
	//this instantiates the random generator
	private Random randGen;


	/*This constructor initializes a new Game object, so that the Application 
	 * can begin calling this games update() method to advance game play. In the 
	 * process of this initialization, all of the objects in the current level 
	 * should be instantiated and initialized to their beginning states.
	 * @param (level)is a string that either contains the word RANDOM, or the 
	 * contents of a custom level that should be loaded and played.
	 * @param (randGen)is a Random number generator that should be used to set
	 *  the initial positions for the 6 Fire objects and 20 Pant objects within
	 *   a random level.
	 */
	public Game(String level, Random randGen) 
	{
		//this sets the random generator instance to the randGen 
		//past into the parameter
		this.randGen = randGen;

		if (level.contains("RANDOM")){
			createRandomLevel();
		}
		else{
			loadLevel(level);
		}
	}// game contstructor



	/*The Application calls this method repeatedly to update all of the objects 
	 * within your game, and to enforce all of the rules of your game.
	 * @param (time)is the time in milliseconds that has elapsed since last 
	 * time this method was called.This can be used to control the speed of 
	 * objects moving in game.
	 * return When this method returns QUIT the game will end after a short 3
	 * second pause and message indicating that the player has lost.
	 * When this method returns ADVANCE, a short pause and win message will 
	 * be followed by the creation of a new game which replaces this one. 
	 * When this method returns anything else (including CONTINUE), 
	 * the Application will simply continue to call this update() method.
	 */
	public String update(int time)
	{
		hero.update(time, water );

		//if any water instance is not null, it is updated
		for (int i = 0; i < water.length; i++){
			if (water[i] != null){
				water[i] = water[i].update(time);
			}// if
		}// water for

		// this updates every pant
		for (Pant pant : pants){
			pant.update(time);
		}// pant for

		//if any fireball instance is not null, then it is updated
		for (Fireball fireball : fireballs){
			if (fireball != null)
				fireball.update(time);
		}// fireball for

		//for every fire instance, its fireball is updated as long it's not 
		//null
		for (Fire fire: fires){
			Fireball newFireball = fire.update(time);
			if(newFireball != null)
				fireballs.add(newFireball);
		}

		//if hero collides with the fireball, then quit the game 
		if(hero.handleFireballCollisions(fireballs) == true){
			return "QUIT";
		}

		//this for loop checks if element in fireball arrayList is not null, 
		//and sees if fireball is colliding with water. 
		for (int i = 0; i < fireballs.size(); i++){
			if (fireballs.get(i) != null)
				fireballs.get(i).handleWaterCollisions(water);
		}



		//checks through the arrayList of fire and sees if it is colliding with
		//water
		for(int i = 0; i < fires.size(); i++){
			fires.get(i).handleWaterCollisions(water);
		}


		//this for loop goes through the pant array list and sees if the pant 
		//is colliding with fireball. if it is, it becomes a fire
		for (int i = 0; i < pants.size(); i++){
			if (pants.get(i).handleFireballCollisions(fireballs) != null){
				float pantXLocation = pants.get(i).getGraphic().getX();
				float pantYLocation = pants.get(i).getGraphic().getY();
				fires.add(new Fire(pantXLocation, pantYLocation, randGen));

			}//if
		}// for
		//this loop iterates through the fireball ArrayList and if fireball is 
		//not null and the should remove method equals true. then remove 
		//fireball
		for(int i = 0; i < fireballs.size(); i++){
			if(fireballs.get(i) != null && fireballs.get(i).shouldRemove() == 
					true){
				fireballs.remove(i);

			}
		}

		//this for loop goes through the pant arrayList and if the should remove
		//method returns true, then remove the pant
		for (int i = 0; i < pants.size(); i++){
			if (pants.get(i).shouldRemove() == true){
				pants.remove(i);
			}
		}
		//this for loop goes through the fires arrayList and removes an element
		//if the should Remove equals true
		for (int i = 0; i < fires.size(); i++){
			if (fires.get(i).shouldRemove() == true){
				fires.remove(i);
			}
		}
		//if the array list of fire equals, zero, advance to new level
		if(fires.size()== 0){
			return "ADVANCE";

		}

		//else return continue
		return "CONTINUE";
	}// update 

	/*This method returns a string of text that will be displayed in the upper 
	 * left hand corner of the screen. Ultimately this should express the number
	 * of unburned pants remaining in the level. However, this may also be 
	 * useful for displaying messages that help you debug your game.
	 * @(return) a string of text to be displayed in the upper-left hand corner 
	 * of the screen by the Application.
	 */
	public String getHUDMessage() { 
		return "Pants left: " + pants.size() + "\n Fires Left: " 
				+ fires.size(); 
	}

	/*This method creates a random level consisting of a single Hero centered in
	 *  the middle of the screen, along with 6 randomly positioned fires, and 20 
	 *  randomly positioned pants.
	 *  @(return) void
	 */
	public void createRandomLevel() {
		controlType = randGen.nextInt(3) + 1;
		// find the width and height of the game and then center the hero 
		//graphic
		float width = Engine.getWidth();
		float height = Engine.getHeight();
		hero = new Hero(width/2, height/2, controlType);


		// create 20 instances of pant in the ArrayList
		for (int i = 0; i < 20; i++){
			pants.add(new Pant(randGen.nextFloat()* Engine.getWidth(),
					randGen.nextFloat() * Engine.getHeight(), randGen));
		}// pants for loop
		// set the pant's x and y coordinate to a randomly generated value

		// create 6 instances of fire in the ArrayList
		for (int i = 0; i < 6; i++){
			fires.add(new Fire(randGen.nextFloat()* Engine.getWidth(),
					randGen.nextFloat() * Engine.getHeight(), randGen));
		}// fire for loop
	}
	/*This method initializes the current game according to the descriptions 
	 * included within the level parameter as described below.
	 * @param (level) is a string containing the contents of a custom level 
	 * file, which can be read using a new Scanner(level). The 
	 * first line is always the ControlType: # where # is either 1, 2, or 3. 
	 * Subsequent lines describe an object TYPE, along with an X and Y position
	 *  that are formatted as: TYPE @ X, Y. This method should instantiate and 
	 *  initialize a new object for each such line.
	 *  @(return) void
	 */
	public void loadLevel(String level) {
		level = level.replace(",","");
		level = level.replace("@", "");
		Scanner input = new Scanner(level);
		String controlType = input.nextLine();

		if (controlType.equals("ControlType: 1")){
			this.controlType = 1;
			while (input.hasNext()){
				String read = input.next();
				if(read.contains("FIRE")){
					float xCoordinate = (float) input.nextDouble();
					float yCoordinate = (float) input.nextDouble();
					fires.add(new Fire(xCoordinate, yCoordinate, randGen));	
				}
				else if(read.contains("HERO")){
					float xCoordinate = (float) input.nextDouble();
					float yCoordinate = (float) input.nextDouble();
					hero = new Hero(xCoordinate, yCoordinate, 
							this.controlType);
				}
				else if(read.contains("PANT")){
					float xCoordinate = (float) input.nextDouble();
					float yCoordinate = (float) input.nextDouble();
					pants.add(new Pant(xCoordinate, yCoordinate, 
							randGen));
				}

			}// while
		}//if 
		else if (controlType.equals("ControlType: 2")){
			this.controlType = 2;
			while (input.hasNext()){
				String read = input.next();
				if(read.contains("FIRE")){
					float xCoordinate = (float) input.nextDouble();
					float yCoordinate = (float) input.nextDouble();
					fires.add(new Fire(xCoordinate, yCoordinate, randGen));
				}
				else if(read.contains("HERO")){
					float xCoordinate = (float) input.nextDouble();
					float yCoordinate = (float) input.nextDouble();
					hero = new Hero(xCoordinate, yCoordinate, 
							this.controlType);
				}
				else if(read.contains("PANT")){
					float xCoordinate = (float) input.nextDouble();
					float yCoordinate = (float) input.nextDouble();
					pants.add(new Pant(xCoordinate, yCoordinate, 
							randGen));
				}

			}// while
		}// else if
		else{
			this.controlType = 3;
			while (input.hasNext()){
				String read = input.next();
				if(read.contains("FIRE")){
					float xCoordinate = (float) input.nextDouble();
					float yCoordinate = (float) input.nextDouble();
					fires.add(new Fire(xCoordinate, yCoordinate, randGen));
				}
				else if(read.contains("HERO")){
					float xCoordinate = (float) input.nextDouble();
					float yCoordinate = (float) input.nextDouble();
					hero = new Hero(xCoordinate, yCoordinate, 
							this.controlType);
				}
				else if(read.contains("PANT")){
					float xCoordinate = (float) input.nextDouble();
					float yCoordinate = (float) input.nextDouble();
					pants.add(new Pant(xCoordinate, yCoordinate, 
							randGen));
				}


			}// while
		}// else
		input.close();
	}

	/*This method creates and runs a new Game and Engine together. Any command 
	 * line arguments are used to choose custom levels that should be played in
	 *  a particular order. It is also possible to seed the Random number 
	 *  generator passed into this Game's constructor by passing a positive 
	 *  integer as string first element within this args array.
	 *  @param (args) is the sequence of custom levels to play, with an optional 
	 *  first element containing a seed for a random number generator.
	 *  @(return) void
	 */
	public static void main(String[] args)
	{
		Application.startEngineAndGame(args);		
	}
}
