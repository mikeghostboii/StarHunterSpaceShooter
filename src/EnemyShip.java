import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;


public class EnemyShip extends Ship{
	
	private int width = 25;
	private int height = 25;

	Rectangle box, body, tip;

	public EnemyShip() {
		super();
	}

	public EnemyShip(int x, int y) {
		super(x, y);
	}

	public void draw(Graphics2D g2) {
		
		box = new Rectangle((int)x, (int)y, width, height);
		g2.setColor(Color.BLACK);
		g2.draw(box);
		g2.fill(box);
		g2.setColor(Color.RED);
		body = new Rectangle((int)x, (int)y, width - 15, height - 15);
		g2.fill(body);
	}
}
