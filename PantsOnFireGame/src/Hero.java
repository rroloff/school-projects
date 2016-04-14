import java.util.ArrayList;

public class Hero {
	//This constructs a single graphic  object at position @ (0,0)
	private Graphic graphic; 
	// This is an instance variable to control speed
	private float   speed;
	// This is an instance variable that controls ControlType
	private int     controlType;


	// This is the Hero Constructor.This places the Hero at a location
	//based on the x and y coordinate as well as the control Type
	public Hero(float x, float y, int controlType){
		speed = 0.12f;
		//this saves the controlType passed from paratmeter into the data field
		this.controlType = controlType;

		graphic = new Graphic ();
		graphic.setType("HERO");
		graphic.setX(x);
		graphic.setY(y);

	}

	/*This method updates the Hero becuase its position changes every time 
	 *the keyboard is pressed and space/ mouse is pressed. This also sprays the 
	 *new water in the direction the hero is currently facing. 
	 *@param(time)this parameter is used to calculate the distance of the object
	 *@param(water Array) this parameter is passed through so that it is updated
	 *with the position & direction of hero
	 *@return void
	 */
	public void update(int time, Water[] water){
		graphic.draw();

		/* If the user holds D, the hero moves to the right,
		 *  A to the left, W moves down, and S moves up. The direction that hero
		 *  is facing depends on where it is going.
		 */
		// this sets the control type to 1
		if (controlType == 1 ){ 	
			if (Engine.isKeyHeld("D")){
				//the graphic's direction is set to right
				graphic.setDirection(0);
				//the x coordinate of hero adds the distance to the graphic's 
				//position
				graphic.setX(graphic.getX() + (speed * time));
			}

			else if (Engine.isKeyHeld("A")){
				//hero is set to the left direction
				graphic.setDirection((float)Math.PI);
				//the distance is subtracted from the hero's x coordinate 
				graphic.setX(graphic.getX() - (speed * time));
			}

			else if (Engine.isKeyHeld("W")){
				//hero's direction is set downwards
				graphic.setDirection(3 * (float)Math.PI/2);
				// the distance is subtracted from the y coordinate
				graphic.setY(graphic.getY() - (speed * time));
			}

			else if (Engine.isKeyHeld("S")){
				//the direction of the hero is set upwards
				graphic.setDirection((float)Math.PI/2);
				//the distance of the graphic is added to the y coordinate 
				graphic.setY(graphic.getY() + (speed * time));
			}
		}// controlType one brace

		/* Has the same movement as the code above. However hero faces the 
		 * direction of the mouse.
		 */

		else if (controlType == 2){
			int x = Engine.getMouseX();
			int y = Engine.getMouseY();
			graphic.setDirection(x,y);

			if (Engine.isKeyHeld("D")){
				graphic.setX(graphic.getX() + (speed * time));
			}
			else if (Engine.isKeyHeld("A")){

				graphic.setX(graphic.getX() - (speed * time));
			}
			else if (Engine.isKeyHeld("W")){
				graphic.setY(graphic.getY() - (speed * time));
			} 
			else if (Engine.isKeyHeld("S")){
				graphic.setY(graphic.getY() + (speed * time));
			}
		}// control type two brace


		/*Starts with getting the hero to face the direction of the mouse cursor
		 *  but instead of moving them according to key presses, the hero will 
		 *  continuously move toward the mouse, until they get within 20 pixels 
		 *  of its location.  
		 */

		else if (controlType == 3){
			int mouseX = Engine.getMouseX();
			int mouseY = Engine.getMouseY();
			int heroX = (int)graphic.getX();
			int heroY= (int)graphic.getY();

			// calculates the distance between the mouse and hero
			int distance = (int) Math.sqrt((Math.pow(heroX-mouseX, 2))+ 
					(Math.pow(heroY-mouseY,2)));

			/*  if the hero's distance is greater than 20 pixels of its location
			 * the hero will continuously move toward the mouse
			 */
			if (distance > 20){
				graphic.setDirection(mouseX, mouseY);
				graphic.setX(mouseX + (graphic.getDirectionX() *(speed * time)));
				graphic.setY(mouseY + (graphic.getDirectionY() *(speed * time)));
			}// if statement	
		}// control type three brace



		//this boolean variable was set to false to make sure that an instance 
		//of water was created, which is later put into the condition 
		boolean instanceOfWaterCreated = false;
		for (int i = 0; i < water.length; i++){

			/* If a null is found and the player is holding down either the space
			 * bar or the left mouse button, then the hero should create one new 
			 * Water object and add the reference to this new Water into the 
			 * previously empty (null) position with the water array. 
			 */
			if (((Engine.isKeyHeld("MOUSE") || Engine.isKeyHeld
					("SPACE"))
					&& water[i] == null && !instanceOfWaterCreated)){
				float heroX = graphic.getX();
				float heroY = graphic.getY();
				float heroDirection = graphic.getDirection();
				water[i] = new Water(heroX, heroY, heroDirection);
				instanceOfWaterCreated = true;
			}// if
		}// for
	}


	/*This method returns heros graphic 
	 * @return Graphic
	 */
	public Graphic getGraphic(){
		return graphic;
	}


	/*This method should return true, if and only if the hero is colliding with
	 *  one of these fireballs in this ArrayList.  This method should be called 
	 *  from the Games update(), and when it returns true the Games update 
	 *  should return QUIT to indicate that the player has lost the game by 
	 *  being hit by a fireball.
	 * @param(ArrayList<Fireball> fireballs) fireballs arrayList are passed 
	 * through because that information is used to find size of arrayList, 
	 * index, and graphic object
	 *@return(boolean) returns true if the hero collides with a fireball 
	 */
	public boolean handleFireballCollisions(ArrayList<Fireball> fireballs) {
		//this iterates through the fireball array to see if fireball is
		//colliding with the hero
		for(int i = 0; i < fireballs.size(); i++){
			Fireball myFireball = fireballs.get(i);
			if(myFireball != null && graphic.isCollidingWith
					((myFireball.getGraphic()))){
				return true;
			}//if
		}//for
		return false;
	}//handleFireballCollision
}
