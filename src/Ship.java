import java.awt.Graphics2D;

public class Ship {

    	double x; 
    	double y; 
    	double dx, dy;
	final int HEIGHT = 50; // Height of the bounding box
	final int WIDTH = 50; // Width of the bounding box
	Ship ship;
	
	public Ship() {
		
	}

	public Ship(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/*
	 * Method that gets X
	 * @return xLeft
	 */
	public double getX() {
		return x;
	}

	/*
	 * Method that gets Y
	 * @return yTop
	 */
	public double getY() {
		return y;
	}
	
	public void setX(double d) {
		this.x = d;
	}

	public void setY(double y) {
		this.y = y;
	}

	/*
	 * Method that sets the position to a certain parameters
	 * @param x, y
	 */
	public void setPosition(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public void draw(Graphics2D g2) {
		// TODO Auto-generated method stub

	}

}
