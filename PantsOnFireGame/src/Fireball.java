
public class Fireball {
	////This constructs a single graphic  object at position @ (0,0)
	private Graphic graphic;
	//this instance variable controls speed
	private float speed;
	//this instance variable is boolean which sets fireball to alive 
	private boolean isAlive = true;

	/*This constructor initializes a new instance of Fireball at the specified 
	 * location and facing a specific movement direction.
	 */
	public Fireball(float x, float y, float directionAngle) {
		graphic = new Graphic ();
		graphic.setType("FIREBALL");
		speed = 0.2F;
		graphic.setX(x);
		graphic.setY(y);
		graphic.setDirection(directionAngle);
	}
	/*This method is called repeatedly by the Game to draw and move the current 
	 * Fireball. When a fireball moves more than 100 pixels beyond any edge of 
	 * the screen, its graphic should be destroyed and its shouldRemove() 
	 * method should return true instead of false.
	 * @param(time)is the amount of time in milliseconds that has elapsed since 
	 * the last time this update was called.
	 * @return (void)
	 */
	public void update(int time) {
		graphic.draw();
		if(Math.abs(graphic.getX())== 100||Math.abs(graphic.getY())== 100){
			graphic.destroy();
			this.isAlive = false;
		}
		else{
			graphic.setX(graphic.getX() + speed * graphic.getDirectionX() * 
					time );
			graphic.setY(graphic.getY() + speed * graphic.getDirectionY() * 
					time);
		}
	}
	/*This is a simple accessor for this objects Graphic, which may be used by 
	 * other objects to check for collisions.
	 * @return (graphic)
	 */
	public Graphic getGraphic(){
		return graphic;

	}
	/*Every time this method finds a Water object colliding with a Fire, that 
	 * Fires heat should be decreased by one.  The Water objects graphic 
	*should also be destroyed and its array position should be reset to null,  
 	*whenever this happens. If the Fires heat ever drops below one, then the 
 	*Fires graphic should be destroyed, it should stop being displayed, and 
 	*it should also stop throwing Fireballs.
 	*@param (water) This is an array of waters that is passed in b/c we need to
 	*see if each element within the water is colliding with the fireball graphic
 	*@return (void)
	 */
	public void handleWaterCollisions(Water[] water) {
		for (int i = 0; i< water.length; i++ ){
			if(water[i]!= null){
				Water myWater = water[i];
				if(graphic.isCollidingWith((myWater.getGraphic()))){
					this.destroy();
					Graphic destroyedWater = myWater.getGraphic();
					destroyedWater.destroy();
					water[i] = null;
				}// if
			}// if
		}//for
	}// handleWaterCollision
	
	/*Whenever you find a Fireball that is colliding with the current Pant, 
	 * destroy the current Pants graphic and prevent it from being display.  
	 * Well also want to destroy the Fireballs graphic and set its isAlive 
	 * field to false.
	 * @return void
	 */
	public void destroy() {
		this.isAlive = false;
		graphic.destroy();
	}
	
	/* Any Fireballs that are not alive according to their isAlive field should 
	 * be removed.  This includes fireballs that have traveled more than 100 
	 * pixels away from the closest screen edge, and fireballs that have 
	 * collided with either Water or Pants.
	 * @return (boolean) true if isAlive is false 
	 */
	public boolean shouldRemove() {
		if(this.isAlive == false){
			return true;
		}
		else
			return false;
		
	}
}
