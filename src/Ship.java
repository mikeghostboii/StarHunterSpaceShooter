import java.awt.Graphics2D;

public class Ship {

    static int x; 
    static int y; 
	final int HEIGHT = 50; // Height of the bounding box
	final int WIDTH = 50; // Width of the bounding box
	Ship ship;

	public Ship() {
		
	}

	@SuppressWarnings("static-access")
	public Ship(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/*
	 * Method that gets X
	 * @return xLeft
	 */
	public int getX() {
		return x;
	}

	/*
	 * Method that gets Y
	 * @return yTop
	 */
	public int getY() {
		return y;
	}
	
	@SuppressWarnings("static-access")
	public void setX(int x) {
		this.x = x;
	}

	@SuppressWarnings("static-access")
	public void setY(int y) {
		this.y = y;
	}

	/*
	 * Method that sets the position to a certain parameters
	 * @param x, y
	 */
	@SuppressWarnings("static-access")
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void draw(Graphics2D g2) {
		// TODO Auto-generated method stub

	}

}
