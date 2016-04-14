
public class Water {
	//This constructs a single graphic  object at position @ (0,0)
	private Graphic graphic;
	//this is an instance variable which controls speed
	private float speed;
	//this is an instance variable which records distance traveled
	private float distanceTraveled = 0;

	/*This constructor initializes a new instance of Water at the specified 
	 *location and facing a specific movement direction
	 *@param(x) this controls x position of the water
	 *@param(y) this controls the y position of water
	 *@ param (direction) the angle (in Radians) from 0 to 2pi that this new 
	 *Water should be both oriented and moving according to.
	 *
	 */
	
	public Water(float x, float y, float direction) {
		speed = 0.7f;

		// create new water graphic
		graphic = new Graphic();
		graphic.setType("WATER");

		// set the direction of water to the direction that hero is facing
		graphic.setX(x);
		graphic.setY(y);
		graphic.setDirection(direction);
	}

	/*This method is called repeatedly by the Game to draw and move the current 
	 * Water. After this Water has moved a total of 200 pixels or further, its 
	 * graphic should be destroyed and this method should return null instead 
	 * of a reference to this Water.
	 * @param (time) is the amount of time in milliseconds that has elapsed 
	 * since the last time this update was called
	 * @return a reference to this water for as long as this water has traveled 
	 * less than 200 pixels. It should then return null, after this water has 
	 * traveled this far.
	 */
	public Water update(int time) {
		graphic.draw();

		/*Each water object should travel no more than 200 pixels, before it 
		 * splashes into the ground. While a Water object has traveled less than
		 * or equal to 200 pixels, it should return a reference to itself.  As 
		 * soon as the Water has traveled more than 200 pixels it should return
		 *  null instead.  
		 */
		if (distanceTraveled <= 200){
			distanceTraveled += speed * time;
			graphic.setX(graphic.getX() + speed * graphic.getDirectionX() * 
					time );
			graphic.setY(graphic.getY() + speed * graphic.getDirectionY() * 
					time);
			return this;
		}
		else{
			return null;
		}
	}
	
	/*this returns the water graphic to any method that calls it
	 * @return graphic
	 */
	public Graphic getGraphic(){
		return graphic;
		
	}
}
