import java.util.ArrayList;
import java.util.Random;

public class Pant {
	// //This constructs a single graphic  object at (0,0).
	private Graphic graphic;
	//this instantiates the random Generator
	private Random randGen;
	
	//This constructor initializes a new instance of Pant at the 
	//appropriate location.
	public Pant(float x, float y, Random randGen) {
		//this creates new instance of graphic 
		graphic = new Graphic();
		graphic.setType("PANT");
		graphic.setX(x);
		graphic.setY(y);
		//this sets the random generator instance to the randGen 
		//past into the parameter 
		this.randGen = randGen;
	}

	/*This method is simply responsible for drawing the current Pant to the 
	 *screen.
	 *@param(time)is the amount of time in milliseconds that has elapsed since 
	 *the last time this update was called.
	 *@return void
	 */
	public void update(int time) {
		graphic.draw();
	}
	/*This method allows any class to access the pants graphic 
	 * @return(graphic)
	 */
	public Graphic getGraphic(){
		return graphic;
	}

	/*Whenever you find a Fireball that is colliding with the current Pant,
	 *  destroy the current Pants graphic and prevent it from being display.  
	 *  Well also want to destroy the Fireballs graphic and set its isAlive 
	 *  field to false. 
	 *  @param(ArrayList<Fireball> fireballs) fireballs arrayList are passed 
	 *  through because that information is used to find size of arrayList, 
	 *  index, and graphic object
	 *  @ return (boolean)returns true if the pant collides with a fireball 
	 */
	public Fire handleFireballCollisions(ArrayList<Fireball> fireballs) {
		//this iterates through the fireball array to see if fireball is
		//colliding with the pant
		for (int i = 0; i < fireballs.size(); i++ ){
			Fireball myFireball = fireballs.get(i);
			if(	myFireball != null && graphic.isCollidingWith(
					(myFireball.getGraphic()))){
				Fire newFire = new Fire(graphic.getX(),graphic.getY(),
						randGen);
				graphic.destroy();
				myFireball.destroy();
				fireballs.set(i, null);
				return newFire;
			}// if
		}//for
		return null;
	}// handleWaterCollisons


	/*Pants that have collided with a Fireball and are therefore no longer being 
	 * drawn to the screen should be removed.
	 */
	public boolean shouldRemove() {
		if (graphic.getType() == null){
				return true;
			}
			else {
				return false;
			}
		}
	}


