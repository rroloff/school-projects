import java.util.Random;

public class Fire {
	//This constructs a single graphic  object at position @ (0,0)
	private Graphic graphic;
	// This instantiates the random generator
	private Random randGen;
	//this keeps track of the fireballs left
	private int fireballCountdown;
	//this keeps track of the heat power left in fire. its highest is 40.
	private int heat = 40;
	
	/*This constructor initializes a new instance of Fire at the appropriate 
	 * location and with the appropriate amount of heat.
	 */
	public Fire(float x, float y, Random randGen) {
		graphic = new Graphic();
		graphic.setType("FIRE");
		graphic.setX(x);
		graphic.setY(y);

		// the fireballCountdown is initialized to a random value between 3000 
		//and 5999 
		fireballCountdown = randGen.nextInt(3000)+2999;
		this.randGen = randGen;

	}
	
	/*This method is called repeatedly by the Game to draw and sporadically 
	 * launch a new Fireball in a random direction.
	 *@param(time) is the amount of time in milliseconds that has elapsed since 
	 *the last time this update was called. 
	 *@return null or reference to the fireball graphic returned
	 */
	public Fireball update(int time) {
		graphic.draw();

		// fireballCountdown is decremented every time the update method is
		//called
		fireballCountdown-=time;


		/*When this value becomes less than or equal to zero, a new fireball 
		 * should be created and this fireballCountdown should be reset to a new 
		 * random value between 3000 and 5999.
		 */
		if (fireballCountdown <= 0){
			float randomFloat = randGen.nextFloat();
			Fireball fireball = new Fireball(graphic.getX(), graphic.getY(),
					(float)(Math.PI * 2.0 * randomFloat));
			int randomInt = randGen.nextInt(2999)+3000;
			fireballCountdown = randomInt;
			return fireball;
		}
		return null;	
	}	
	/*This is a simple accessor for this objects Graphic, which may be used by 
	 * other objects to check for collisions.
	 * @return graphic
	 */
	public Graphic getGraphic(){
		return graphic;

	}
	/*Every time this method finds a Water object colliding with a Fire,
	 *Fires heat should be decreased by one.  The Water objects graphic should 
	 *also be destroyed and its array position should be reset to null, 
	 *whenever this happens. If the Fires heat ever drops below one, then the 
	 *Fires graphic should be destroyed, it should stop being displayed, 
	 *and it should also stop throwing Fireballs.
	 *@param(water) this is an array of waters that passed into the method to
	 *see if the fire graphic is colliding with instance of water 
	 *@return(void)
	 */
	public void handleWaterCollisions(Water[] water) {
		for (int i = 0; i< water.length; i++ ){
			if(water[i]!= null){
				Water myWater = water[i];
				if(graphic.isCollidingWith((myWater.getGraphic()))){
					heat--;
					Graphic destroyedWater = myWater.getGraphic();
					destroyedWater.destroy();
					water[i] = null;
					if(heat < 1){
						graphic.destroy();
					}//if
				}// if
			}// if 
		}//for
	}// handleWaterCollision
	
	/*This method should return false until this Fires heat drops down to 0. 
	 * After that it should begin to return true.
	 * @returns(boolean)true when this Fires heat is greater than 0,else false
	 */
	public boolean shouldRemove() {
		if (heat < 1){
			return true;	
		}
		else 
			return false;
	}// should remove
}
