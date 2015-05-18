import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;


public class EnemyShip extends Ship{
	
	private int x, y;
	private int width = 25;
	private int height = 25;

	Rectangle box, body, tip;

	public EnemyShip() {
		super();
	}

	public EnemyShip(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void draw(Graphics2D g2) {
		int xCor = x;
		int yCor = y;
		box = new Rectangle(xCor, yCor, width, height);
		g2.draw(box);
		g2.fill(box);
		g2.setColor(Color.RED);
		body = new Rectangle(x, y, width - 15, height - 15);
		g2.fill(body);
	}

}
